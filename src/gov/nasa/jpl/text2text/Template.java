package gov.nasa.jpl.text2text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.LinkedList;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import gov.nasa.jpl.text2text.TemplateParser.*;
import gov.nasa.jpl.text2text.TranslationParser.*;

import gov.nasa.jpl.jsonPath2.*;

import static gov.nasa.jpl.jsonPath2.Utils.*;

/**
 * Specifies a code pattern in the target language.
 * Generally, an individual template should represent a single syntactically complete construct in the target language.
 * 
 * @author David K. Legg
 *
 */
class Template {
  /// Private constants
  
  //TODO: abstract these constants to a config file
  private static final String CHILD_TEMPLATE_MODIFIER = "_WITHIN_";
  private static final String DEFAULT_FIELD_FORMAT    = "%s";
  private static final String DEFAULT_VALUE_JOINER    = " ";
  private static final String DEFAULT_BODY_JOINER     = "\n";
  private static final String DEFAULT_BODY_INDENT     = "";
  private static final String FIELD_PREFIX            = "%";
  private static final String DEFAULT_ALTERNATE_SANITIZER = "";
  
  /// Private fields
  
  private String name;
  private List<Component> components;
  private Map<String,Field> fields;
  private Field triggerField;
  private TemplateDataSource dataSource;
  private TranslationDescription translationDescription;
  private Logger logger;
  private Boolean noDuplicates;
  
  /// Public Methods

  /**
   * Construct a template by parsing its textual representation
   * @param stringForm The textual representation for the Template, as given by {@link Template#toString()}
   * @throws S2KParseException if parsing fails for any reason
   */
  public Template(String stringForm) throws IllegalArgumentException {
    this( fromString(stringForm) );
  }

  /**
   * Parses the given textual representation of a Template
   * @param templateStr the textual representation of a Template, as given by {@link Template#toString()}
   * @return The Template specified by templateStr 
   * @throws S2KParseException if parsing fails for any reason
   */
  public static Template fromString(String templateStr) throws IllegalArgumentException {
    return fromString(templateStr, new TranslationDescription());
  }
  public static Template fromString(String templateStr, TranslationDescription tranlsationDescription) throws IllegalArgumentException {
	  TemplateLexer lexer = new TemplateLexer(CharStreams.fromString(templateStr));
    TemplateParser parser = new TemplateParser( new CommonTokenStream(lexer) );
    TemplateVisitor templateVisitor = new TemplateVisitor();
    return templateVisitor.visit( parser.template() ).orElseThrow( () -> new IllegalArgumentException("Could not parse string as a template.") );
  }
  
  /**
   * Builds an inner template. 
   * @param parent The Template in which this Template is contained.
   * @return A copy of this template, with appropriate modifications to be contained in the given parent.
   */
  public Template asChildOf(Template parent) {
    Template output     = new Template(this);
    output.name         = this.name + CHILD_TEMPLATE_MODIFIER + parent.name;
    return output;
  }
  
  /**
   * @return This Template's name.
   */
  public String getName() {
    return name;
  }
  
  /**
   * @return Trigger field's name.
   */
  public String getTriggerName() {
    return this.triggerField.name;
  }
  
  /**
   * @return The enclosing TranslationDescription for this Template.
   */
  public TranslationDescription getTranslationDescription() {
    return translationDescription;
  }
  
  /**
   * @return A Collection of Fields used in this Template.
   */
  public Collection<Field> getFields() {
    return fields.values();
  }
  
  /**
   * @return True if this Template contains a recursive field.
   */
  public Boolean isRecursive() {
    return fields.values().stream().anyMatch( f -> f.isBody );
  }
  
  /**
   * If this is true, this template should only be used composed inside of other templates,
   * never as a stand-alone construct.
   * This is denoted by making the PARENT_REFERENCE field "Required"
   * @return True if this Template's PARENT_REFERENCE field is present and required
   */
  public Boolean isIncomplete() {
    return Optional.ofNullable( fields.get("PARENT_REFERENCE") )
        .map( f -> f.isNecessary )
        .orElse( false );
  }
  
  /**
   * @return All the Templates in the enclosing TranslationDescription.
   */
  public Collection<Template> getAllTemplates() {
    return translationDescription.values();
  }
  
  /**
   * Uses the dataSource given to find all instances of this template in the source.
   * @param dataSource The TemplateDataSource describing how to retrieve information for this template.
   * @param source The source model to draw information from.
   * @return A Collection of TemplateMatches, each representing an instance of the template in the source.
   */
  public Collection<TemplateMatch> matchToSource(JsonNode source, Collection<JsonNode> libraries) {
    if (!dataSource.containsKey( getTriggerName() )) {
      logger.warning("Template %s has no path for its trigger.", Logger.VERBOSITY.QUIET, name);
      return Collections.emptyList();
    }
    Collection<JsonNode> triggerValues = dataSource.get( getTriggerName() ).access(source, libraries);
    
    return triggerValues.stream()
        .map( triggerValue -> {
          TemplateMatch match = new TemplateMatch(this);
          
          match.put( getTriggerName(), Collections.singletonList(triggerValue) );
          match.setTriggerValue( triggerValue );
          
          dataSource.keySet().stream()
            .filter( fieldName -> !fieldName.equals(getTriggerName()) ) // for any field but the triggering field...
            .forEach( fieldName -> {
              logger.debug("Field %s ; path %s", Logger.VERBOSITY.VERBOSE, fieldName, dataSource.get(fieldName));
              // access that path, with this trigger's reference object
              List<JsonNode> relativeLookup = new ArrayList<JsonNode>( dataSource.get(fieldName).access(source, triggerValue, libraries) );
              relativeLookup.sort(null); // guarantee a deterministic (but arbitrary) ordering of the nodes
              logger.debug("%d results", Logger.VERBOSITY.VERBOSE, relativeLookup.size());
//              relativeLookup.stream()
//                .findAny() // choose arbitrarily from the access result
//                .ifPresent( value -> match.put(fieldName, value) ); // if we have a path, add it to the match
              match.put(fieldName, relativeLookup); // add the entire access result here
            });
          return match;
        })
        .filter( this::isValidMatch )
        .collect( Collectors.toList() );
  }
  
  /**
   * Builds the target code, and registers it with the instantiation registrar.
   * @param templateMatch The TemplateMatch that describes information for this template.
   * @param matrchRegistrar The MatchRegistrar for this translation.
   */
  public void instantiate(TemplateMatch templateMatch, InstantiationRegistrar instantiationRegistrar) {
    String instantiation = "";
    
    for (Component comp : components) {
      if (comp.isField) {
        Field field = fields.get(comp.content);
        if (field == null) {
          System.err.println("Warning! Field component '" + comp.content + "' skipped because field was not found.");
          continue;
        }
        if (field.isBody) {
          // If present, makes sure that matches came from a specified sub-tree
          Predicate<TemplateMatch> matchesRecursive = dataSource.containsKey(field.name) ?
              match -> match.getTriggerValue().map( triggerValue ->
                    templateMatch.get(field.name).stream()
                      .anyMatch( node -> node.contains(triggerValue) )).orElse(false) :
              match -> true; // if there's no path, accept anything
          
          // lookup all instantiations that list this as their parent, and join them by the separator.
//          String recursiveInstance = indent( instantiationRegistrar.get( templateMatch.get( triggerField.name ) ).stream()
          String recursiveInstance = templateMatch.getFirst(triggerField.name)
              .map( instantiationRegistrar::get )
              .orElse( Collections.emptyList() )
              .stream()
              .filter( matchesRecursive )
              .map( TemplateMatch::getInstance )
              .flatMap( optStream() )
              .collect( Collectors.joining(field.bodyJoiner) );
          
          if (!recursiveInstance.isEmpty()) {
            instantiation += indent( String.format(field.formatString, recursiveInstance), field.bodyIndent);
          } else if (field.isNecessary) {
            return; // quit early, necessary body had no sub-instances
          }
        } else {
          Optional<String> value = instantiateField(field, templateMatch);
          if (value.isPresent() || !field.isNecessary) {
            instantiation += value.orElse("");
          } else {
            return; // quit early if a necessary field isn't available
          }
        }
      } else {
        instantiation += comp.content;
      }
    }
    
    if (noDuplicates &&
        instantiationRegistrar.values().stream().flatMap( Collection::stream )
          .map( TemplateMatch::getInstance )
          .flatMap( optStream() )
          .anyMatch( instantiation::equals ) ) {
      return; // without adding this template
    }
    
    templateMatch.setInstance(instantiation);
    instantiationRegistrar.register(templateMatch);
  }

  /**
   * Processes the data in templateMatch for field, according to the flags set in field
   * @param field the field to instantiate
   * @param templateMatch the source data to use
   * @return the processed match data
   */
  public Optional<String> instantiateField(Field field, TemplateMatch templateMatch) {
    Function<String, String> processValue = value -> {
      if (!field.isRaw) {
        value = translationDescription.plugins.sanitizer.apply(value);
      } else if (!field.alternateSanitizer.equals(DEFAULT_ALTERNATE_SANITIZER)) {
        value = translationDescription.plugins.getSanitizer(field.alternateSanitizer).apply(value);
      }
      
      Optional<String> nativeOpt = translationDescription.plugins.nativeFn.apply(value);
      if (nativeOpt.isPresent()) {
        if (field.isKeyword) {
          value = nativeOpt.get(); // use the keyword match
        } else {
          value = "__" + value; // sanitize away the keyword match
        }
      }
      return value;
    };
    
    String value;
    if (field.isMulti) {
      value = templateMatch.getOrDefault(field.name, Collections.emptyList()).stream()
           .map( node -> node.as(ValueNode.class) )
           .flatMap( optStream() )
           .map( ValueNode::asText )
           .map( processValue )
           .collect( Collectors.joining(field.valueJoiner) );
      
      
    } else {
      value = processValue.apply(
          templateMatch.getFirst(field.name)
            .flatMap( node -> node.as(ValueNode.class) )
            .map( ValueNode::asText )
            .orElse("") );
    }
    
    if (value.isEmpty()) {
      if (field.isNecessary) {
        return Optional.empty(); // fail if field is necessary
      } else {
        return Optional.of("");
      }
    } else {
      return Optional.of(String.format(field.formatString, value)); // otherwise, return an empty field
    }
  }
  
  /**
   * Returns textual representation of this Template
   * @return Text representation of template, which can be parsed by {@link Template#fromString(String)}
   */
  public String toString() {
    return name + "\n" + components.stream()
        .map( comp -> comp.isField ? fields.get(comp.content).toString() : comp.content )
        .collect( Collectors.joining() );
  }

  /**
   * Convert to JSON object, using strict format
   * @return a JSON object, completely specifying the Template
   */
  public JSONObject toJSON() {
    return toJSON(true);
  }
  /**
   * Convert to JSON object
   * If non-strict format is used, resulting object will be more compact, but less robust to future change.
   * @param strict Whether to use strict (all JSON) or non-strict (mixed JSON/toString)
   * @return a JSON object completely specifying the Template
   */
  public JSONObject toJSON(boolean strict) {
    if (strict) {
      return new JSONObject()
          .put("_type", "Template")
          .put("name", name)
          .put("components", components.stream()
              .map( Component::toJSON )
              .collect( Collectors.toList() ))
          .put("fields", fields.values().stream()
              .map( Field::toJSON )
              .collect( Collectors.toList() ))
          .put("triggerField", triggerField.toJSON());
    } else {
      return new JSONObject()
          .put("template", this.toString())
          .put("fields", fields.values().stream()
              .map( field -> field.toJSON(strict) )
              .collect( Collectors.toList() ));
    }
  }

  /**
   * Construct a Template based on the given JSON object.
   * Will accept either strict or non-strict formats produced by toJSON, or some mixes of them.
   * @param jsonObj The JSON object to convert
   * @return The Template specified by the JSON
   * @throws S2KParseException if conversion fails for any reason
   * @see Template#fromJSON(JSONObject, String)
   * @see Template#fromJSON(JSONObject, TranslationDescription)
   * @see Template#fromJSON(JSONObject, String, TranslationDescription)
   */
  public static Template fromJSON(JSONObject jsonObj) throws IllegalArgumentException {
    return fromJSON(jsonObj, null, null);
  }
  /**
   * Construct a Template based on the given JSON object.
   * Will accept either strict or non-strict formats produced by toJSON, or some mixes of them.
   * @param jsonObj The JSON object to convert
   * @param name Overrides the name of this template if non-null. Especially useful for non-strict format.
   * @return The Template specified by the JSON
   * @throws S2KParseException if conversion fails for any reason
   * @see Template#fromJSON(JSONObject)
   * @see Template#fromJSON(JSONObject, TranslationDescription)
   * @see Template#fromJSON(JSONObject, String, TranslationDescription)
   */
  public static Template fromJSON(JSONObject jsonObj, String name) throws IllegalArgumentException {
    return fromJSON(jsonObj, name, null);
  }
  /**
   * Construct a Template based on the given JSON object.
   * Will accept either strict or non-strict formats produced by toJSON, or some mixes of them.
   * @param jsonObj The JSON object to convert
   * @param translationDescription Sets the enclosing translationDescription if non-null.
   * @return The Template specified by the JSON
   * @throws S2KParseException if conversion fails for any reason
   * @see Template#fromJSON(JSONObject)
   * @see Template#fromJSON(JSONObject, String)
   * @see Template#fromJSON(JSONObject, String, TranslationDescription)
   */
  public static Template fromJSON(JSONObject jsonObj, TranslationDescription translationDescription) throws IllegalArgumentException {
    return fromJSON(jsonObj, null, translationDescription);
  }
  /**
   * Construct a Template based on the given JSON object.
   * Will accept either strict or non-strict formats produced by toJSON, or some mixes of them.
   * @param jsonObj The JSON object to convert
   * @param name Overrides the name of this template if non-null. Especially useful for non-strict format.
   * @param translationDescription Sets the enclosing translationDescription if non-null.
   * @return The Template specified by the JSON
   * @throws S2KParseException if conversion fails for any reason
   * @see Template#fromJSON(JSONObject)
   * @see Template#fromJSON(JSONObject, String)
   * @see Template#fromJSON(JSONObject, TranslationDescription)
   */
  public static Template fromJSON(JSONObject jsonObj, String name, TranslationDescription translationDescription) throws IllegalArgumentException {
    try {
      if (!jsonObj.optString("_type", "Template").equals("Template")) {
        throw new IllegalArgumentException("Could not parse JSON as a Template.");
      }
      
      Template output;
      if (!jsonObj.optString("template").isEmpty()) {
        // non-strict form
        output = Template.fromString(jsonObj.getString("template"), translationDescription); // grab most of our info from the string format, assume there were no overrides
        JSONArray fieldArr = jsonObj.optJSONArray("fields");
        if (fieldArr != null) {
          for (int i = 0; i < fieldArr.length(); ++i) {
            System.out.println( fieldArr.get(i) );
            output.putField( Field.fromJSON( fieldArr.get(i) ));
          }
        }
      } else {
        // strict-form:
        output = new Template(); // we'll set all the fields manually
        output.name = jsonObj.getString("name");
        JSONArray componentArr = jsonObj.getJSONArray("components");
        for (int i = 0; i < componentArr.length(); ++i) {
          output.components.add( Component.fromJSON( componentArr.get(i) ));
        }
        JSONArray fieldArr = jsonObj.getJSONArray("fields");
        for (int i = 0; i < fieldArr.length(); ++i) {
          output.putField( Field.fromJSON( fieldArr.get(i) ));
        }
        output.triggerField = Field.fromJSON( jsonObj.get("triggerField") );
      }
      JSONObject dataSourceObject = jsonObj.optJSONObject("templateDataSource");
      if (dataSourceObject != null) {
        output.dataSource = TemplateDataSource.fromJSON(dataSourceObject);
      }
      if (name != null) {
        output.name = name;
      }
      if (translationDescription != null) {
        output.translationDescription = translationDescription;
      }
      return output;
      
    } catch (JSONException e) {
      throw new IllegalArgumentException("Could not parse JSON as a Template.", e);
    }
  }
  
  /**
   * Sets the translation description referenced by this template, adds template to that TD, and removes template from its current TD (if such exists).
   * @param td The new Translation Description object, which will overwrite any existing reference in this template.
   */
  public void setTranslationDescription(TranslationDescription td) {
    if (translationDescription != null && translationDescription.containsKey(name)) {
      translationDescription.remove(name);
    }
    translationDescription = td;
    translationDescription.add(this);
  }
  /**
   * Sets the logger that this Template will use for logging messages.
   * @param newLogger The Logger instance this Template will refer to (reference, not copy).
   */
  public void setLogger(Logger newLogger) {
    this.logger = newLogger;
  }
  /**
   * Checks this object for erroneous states and reports them.
   * @return A List of error conditions, if any exist, or an empty list if object is fine.
   */
  public List<String> errors() {
    List<String> output = new LinkedList<String>();
    
    if (name == null || name.isEmpty()) {
      output.add("Template has no name");
      name = "";
    }
    if (components == null || components.isEmpty()) output.add("Template "+name+" has no content.");
    if (fields == null) output.add("Template "+name+" fields map was null.");
    if (triggerField == null) output.add("Template "+name+" has no trigger field.");
    if (dataSource == null) output.add("Template "+name+" has no data source.");
    if (translationDescription == null) output.add("Template "+name+" has no reference to its translation description.");
    if (translationDescription != null && !translationDescription.containsKey(name)) output.add("Translation description does not contain template "+name+".");
    
    if (components != null && fields != null) {
      for (Component c : components) {
        if (c.isField && !fields.containsKey(c.content)) {
          output.add("Field "+c.content+" is in template "+name+" but not its fields list.");
        }
      }
    }
    if (fields != null && dataSource != null) {
      for (Field f : fields.values()) {
        if (!f.isBody && !dataSource.containsKey(f.name)) {
          output.add("Non-body field "+f.name+" in template "+name+" has no data source.");
        }
      }
    }
    
    return output;
  }
  
  
  /// Private Methods
  
  /**
   * An incomplete constructor, should only be used internally.
   * Initializes fields to proper empty/default values.
   */
  private Template() {
    this.components = new LinkedList<Component>();
    this.fields     = new LinkedHashMap<String, Field>();
    this.dataSource = new TemplateDataSource();
    this.translationDescription = new TranslationDescription();
    this.logger     = Logger.SILENT_LOGGER;
    this.noDuplicates = false;
  }
  
  /**
   * shallow copy constructor
   * @param other The Template to copy
   */
  private Template(Template other) {
    this();
    this.name            = other.name;
    this.components      = other.components;
    this.fields          = other.fields;
    this.triggerField    = other.triggerField;
    this.dataSource      = other.dataSource;
    this.translationDescription = other.translationDescription;
  }
  
  /**
   * Adds a field, with appropriate updates to the Template
   * @param field the field to add to this Template
   */
  private void putField(Field field) {
    field = new Field(field); // shallow copy, so we can safely change it:
    if (fields.containsKey(field.name)) {
      field.bodyIndent = fields.get(field.name).bodyIndent;
    }
    fields.put(field.name, field);
    if (field.isTrigger) {
      triggerField = field;
    }
  }
  
  /**
   * Checks that the given match contains all necessary fields
   * @param templateMatch the match to check
   * @return true if and only if all necessary, non-recursive fields are present and non-empty
   */
  private Boolean isValidMatch(TemplateMatch templateMatch) {
    return this.fields.values().stream()
        .filter( field -> field.isNecessary && !field.isBody )
        .map( field -> field.name )
        .map( templateMatch::get )
        .allMatch( nodes -> nodes != null && !nodes.isEmpty() ); // nodes must be present in templateMatch, and have one or more entries
  }
  
  /**
   * Adds the recursive indent for this Template to each line of content
   * @param content the text to indent
   * @param bodyIndent the indent to use for each line
   * @return content with each line preceded by recursive indent
   */
  private String indent(String content, String bodyIndent) {
    return content.replaceAll("\n", "\n" + bodyIndent);
  }
  
  /// Public Sub-classes
  
  /**
   * Represents a substitution point in a Template
   * 
   * @author David K. Legg
   *
   */
  public static class Field {
    public String name;
    public String formatString;
    public String bodyJoiner;
    public String valueJoiner;
    public String alternateSanitizer;
    public boolean isTrigger;
    public boolean isKeyword;
    public boolean isRaw;
    public boolean isBody;
    public boolean isNecessary;
    public boolean isMulti;
    
    public String bodyIndent; // can be added by template based on context of field
    
    /// Public methods
    
    /**
     * Shallow copy constructor, which also enforces flag sanity
     * @param other the Field to copy
     * @see {@link Field#Field(String)}
     * @see {@link Field#Field(String, String, String, boolean, boolean, boolean, boolean, boolean)}
     */
    public Field(Field other) {
      // copy all fields, but use constructor to verify validity
      this( other.name,
            other.formatString,
            other.valueJoiner,
            other.bodyJoiner,
            other.alternateSanitizer,
            other.bodyIndent,
            other.isTrigger,
            other.isKeyword,
            other.isRaw,
            other.isBody,
            other.isNecessary,
            other.isMulti );
    }
    /**
     * Constructs a Field with given name and defaults for all other values
     * @param name the name for this Field, as it will appear in toString() format
     * @see {@link Field#Field(Field)}
     * @see {@link Field#Field(String, String, String, boolean, boolean, boolean, boolean, boolean)}
     */
    public Field(String name) {
      this(name, null, null, null, null, null, false, false, false, false, false, false);
    }
    /**
     * Constructs a Field wth given name and flags
     * Except for name, all fields can be given null to use default values.
     * Note that this method will enforce sanity of flags. In particular:
     * <ul>
     *   <li>Recursive implies Long</li>
     *   <li>Long implies Raw</li>
     *   <li>Trigger implies Necessary</li>
     * </ul>
     * Finally, the bodyJoiner is only meaningful for recursive (body) Fields,
     * and the Long and Raw flags are ignored for recursive Fields.
     * @param name The name for this Field. Must be a legal identifier, as specified in Field grammar
     * @param formatString Java format String to instantiate this field, using %s to access field value
     * @param bodyJoiner The separator to insert between instances of recursive templates. Meaningless for non-recursive fields. 
     * @param isTrigger True if this field should trigger the instantiation of the Template.
     * @param isKeyword True if this field may be a keyword, and should be inserted into target code as-is
     * @param isRaw True if this field should not be sanitized, but may be a keyword in target language
     * @param isRecursive True if this field is instantiated by other (child) Templates
     * @param isNecessary True if missing this field should cancel instantiation of the Template
     * @see {@link Field#Field(Field)}
     * @see {@link Field#Field(String)}
     */
    public Field(String name, String formatString, String valueJoiner, String bodyJoiner, String alternateSanitizer, String bodyIndent, boolean isTrigger, boolean isKeyword, boolean isRaw, boolean isRecursive, boolean isNecessary, boolean isMulti) {
      this.name = name;
      this.formatString = (formatString == null ? DEFAULT_FIELD_FORMAT : formatString);
      this.bodyJoiner   = (bodyJoiner == null ? DEFAULT_BODY_JOINER : bodyJoiner);
      this.valueJoiner  = (valueJoiner == null ? DEFAULT_VALUE_JOINER : valueJoiner);
      this.bodyIndent   = (bodyIndent == null ? DEFAULT_BODY_INDENT : bodyIndent);
      this.alternateSanitizer = (alternateSanitizer == null ? DEFAULT_ALTERNATE_SANITIZER : alternateSanitizer);
      this.isTrigger    = isTrigger;
      this.isBody       = isRecursive;
      this.isKeyword    = isKeyword || this.isBody;
      this.isRaw        = isRaw || this.isKeyword;
      this.isNecessary  = isNecessary || this.isTrigger; // a trigger is, by definition, necessary
      this.isMulti      = isMulti && !this.isBody;
    }

    public void setFormatString(String formatString) {
      this.formatString = (formatString == null ? DEFAULT_FIELD_FORMAT : formatString);
    }
    public void setValueJoiner(String valueJoiner) {
      this.valueJoiner = (valueJoiner == null ? DEFAULT_VALUE_JOINER : valueJoiner);
    }
    public void setBodyJoiner(String bodyJoiner) {
      this.bodyJoiner = (bodyJoiner == null ? DEFAULT_BODY_JOINER : bodyJoiner);
    }
    public void setBodyIndent(String bodyIndent) {
      this.bodyIndent = (bodyIndent == null ? DEFAULT_BODY_INDENT : bodyIndent);
    }
    public void setAlternateSanitizer(String alternateSanitizer) {
      this.alternateSanitizer = (alternateSanitizer == null ? DEFAULT_ALTERNATE_SANITIZER : alternateSanitizer);
    }
    public void setTrigger(boolean isTrigger) {
      this.isTrigger = isTrigger;
      if (isTrigger) {
        this.isNecessary = true;
      }
    }
    public void setBody(boolean isBody) {
      this.isBody = isBody;
      if (isBody) {
        this.isKeyword = true;
        this.isRaw = true;
      }
    }
    public void setKeyword(boolean isKeyword) {
      this.isKeyword = isKeyword;
      if (isKeyword) {
        this.isRaw = true;
      }
    }
    public void setRaw(boolean isRaw) {
      this.isRaw = isRaw;
    }
    public void setNecessary(boolean isNecessary) {
      this.isNecessary = isNecessary;
    }
    public void setMulti(boolean isMulti) {
      this.isMulti = isMulti;
    }

    
    // /**
    //  * Parses the given String as a Field, according to the Field grammar
    //  * @param fieldStr A textual representation of the field, in the format of {@link Field#toString()}
    //  * @return A Field, with all appropriate flags and fields set and flag sanity enforced
    //  * @throws S2KParseException
    //  * @see {@link Field#Field(String, String, String, boolean, boolean, boolean, boolean, boolean)}
    //  */
    // public static Field fromString(String fieldStr) throws S2KParseException {
    //   try {
    //     TemplateLexer lexer = new TemplateLexer( CharStreams.fromString(fieldStr) );
    //     TemplateParser parser = new TemplateParser( new CommonTokenStream(lexer) );
    //     FieldVisitor fieldVisitor = new FieldVisitor();
    //     return fieldVisitor.visit( parser.field() ); 
    //   } catch (Exception e) {
    //     throw new S2KParseException("Could not parse string as a Field.");
    //   }
    // }

    public static Field fromString(String fieldStr) throws IllegalArgumentException {
      String name = fieldStr.replace(FIELD_PREFIX, "");
      if (name.matches("[a-zA-Z0-9_]+")) {
        return new Field(name);
      } else {
        throw new IllegalArgumentException("Could not parse string as a Field. (Are you using the old format?)");
      }
    }
    
    /**
     * Builds the regular expression to match and capture this field in a piece of target code
     * Should only be used in conjunction with a full template regular expression.
     * @return A regular expression String built according to flags set in this Field
     */
    public String toRegexStr() {
      // Explanation: if long type, use a non-greedy "match-anything" pattern
      // otherwise, assume we're looking for an identifier, and use a greedy, non-empy "match-word" pattern
      return String.format("(?<%s>%s)", name, (isKeyword ? ".*?" : "\\\\w+"));
    }

    // /**
    //  * Returns textual representation of this Field, which can be parsed by {@link Field#fromString(String)}
    //  * @return The properly formatted text representation of this Field, with canonical flag ordering
    //  */
    // public String toString() {
    //   Function<String, String> escape = s -> "\"" + StringEscapeUtils.escapeJava(s) + "\"";
    //
    //   return FIELD_PREFIX +
    //          escape.apply(formatString) +
    //          (isTrigger   ? TRIGGER_FLAG : "") +
    //          (isKeyword      ? LONG_FLAG    : "") +
    //          (isRaw       ? RAW_FLAG     : "") +
    //          (isNecessary ? NECESS_FLAG  : "") +
    //          (isRecursive ? RECUR_FLAG + escape.apply(bodyJoiner) : "") +
    //          name;
    // }

    public String toString() {
      return FIELD_PREFIX + name;
    }
    
    /**
     * Converts this Field to a JSON object, using strict format
     * @return a JSON object completely specifying this Field
     * @see {@link Field#toJSON(boolean)}
     * @see {@link Field#fromJSON(Object)}
     */
    public Object toJSON() {
      return toJSON(true);
    }
    /**
     * Converts this Field to a JSON object
     * If non-strict format is used, this is equivalent to {@link Field#toString()}
     * @param strict Whether to use strict (JSON object) or non-strict (String) format
     * @return a JSON object completely specifying this Field
     * @see {@link Field#toJSON()}
     * @see {@link Field#fromJSON(Object)}
     */
    public Object toJSON(boolean strict) {
      // if (strict) {
        return new JSONObject()
            .put("_type", "Field")
            .put("name", name)
            .put("isTrigger", isTrigger)
            .put("isKeyword", isKeyword)
            .put("isRaw", isRaw)
            .put("isBody", isBody)
            .put("isNecessary", isNecessary)
            .put("bodyJoiner", bodyJoiner)
            .put("bodyIndent", bodyIndent)
            .put("formatString", formatString)
            ;
      // } else {
      //   return this.toString();
      // }
    }

    /**
     * Constructs a Field based on the given JSON object.
     * Will accept either strict or non-strict formats produced by <code>toJSON</code>
     * @param jsonObj The JSON object to convert
     * @return The Field specified by the JSON
     * @throws S2KParseException if conversion fails for any reason
     * @see {@link Field#toJSON()}
     * @see {@link Field#toJSON(boolean)}
     */
    public static Field fromJSON(Object jsonObj) throws IllegalArgumentException {
      try {
        if (jsonObj instanceof String) {
          return Field.fromString((String) jsonObj);
        } // else:
        
        JSONObject trueJsonObj = (JSONObject) jsonObj;
        if (!trueJsonObj.getString("_type").equals("Field")) {
          throw new IllegalArgumentException("Could not parse JSON as a Field.");
        }
        return new Field(
            trueJsonObj.getString("name"),
            trueJsonObj.optString("formatString", DEFAULT_FIELD_FORMAT),
            trueJsonObj.optString("valueJoiner", DEFAULT_VALUE_JOINER),
            trueJsonObj.optString("bodyJoiner", DEFAULT_BODY_JOINER),
            trueJsonObj.optString("alternateSanitizer", DEFAULT_ALTERNATE_SANITIZER),
            trueJsonObj.optString("bodyIndent", DEFAULT_BODY_INDENT),
            trueJsonObj.optBoolean("isTrigger", false),
            trueJsonObj.optBoolean("isKeyword", false),
            trueJsonObj.optBoolean("isRaw", false),
            trueJsonObj.optBoolean("isBody", false),
            trueJsonObj.optBoolean("isNecessary", false),
            trueJsonObj.optBoolean("isMulti", false));
      } catch (JSONException e) {
        throw new IllegalArgumentException("Could not parse JSON as a Field.");
      }
    }
    
    /// Private inner classes
    
    /**
     * An extension to the Antlr4 Visitor, to connect parser to Field constructors.
     * 
     * @author David K. Legg
     *
     */
    // private static class FieldVisitor extends TemplateParserBaseVisitor<Field> {
    //   @Override
    //   public Field visitField(TemplateParser.FieldContext ctx) {
    //     Function<String, String> stripQuotes = s -> s.replaceAll("^\"|\"$", "");
        
    //     String formatString = 
    //         (ctx.formatstring() == null ?
    //             DEFAULT_FIELD_FORMAT : 
    //             StringEscapeUtils.unescapeJava( stripQuotes.apply( ctx.formatstring().getText() ) ));
        
    //     String bodyJoiner = ctx.flag().stream()
    //         .map( TemplateParser.FlagContext::recursiveflag )
    //         .filter( x -> x != null )
    //         .findAny()
    //         .map( TemplateParser.RecursiveflagContext::bodyJoiner )
    //         .map( TemplateParser.bodyJoinerContext::getText )
    //         .map( stripQuotes ) // delete surrounding quotes
    //         .map( StringEscapeUtils::unescapeJava ) // unescape the contents
    //         .orElse(DEFAULT_RECUR_SEPARATOR);
        
    //     String alternateSanitizer = ctx.flag().stream()
    //         .map( TemplateParser.FlagContext::rawflag )
    //         .filter( x -> x != null )
    //         .findAny()
    //         .map( TemplateParser.RawflagContext::alternatesanitizer )
    //         .map( TemplateParser.AlternatesanitizerContext::getText )
    //         .map( stripQuotes )
    //         .map( StringEscapeUtils::unescapeJava )
    //         .orElse(DEFAULT_ALTERNATE_SANITIZER);
        
    //     Predicate<Function<TemplateParser.FlagContext, ParserRuleContext>> hasFlag = flagFn -> 
    //         ctx.flag().stream()
    //             .map( flagFn )
    //             .anyMatch( x -> x != null );
        
    //     Field output = new Field( ctx.fieldname().getText(),
    //                               formatString,
    //                               bodyJoiner,
    //                               alternateSanitizer,
    //                               DEFAULT_RECUR_INDENT,
    //                               hasFlag.test( TemplateParser.FlagContext::triggerflag ),
    //                               hasFlag.test( TemplateParser.FlagContext::longflag ),
    //                               hasFlag.test( TemplateParser.FlagContext::rawflag ),
    //                               hasFlag.test( TemplateParser.FlagContext::recursiveflag ),
    //                               hasFlag.test( TemplateParser.FlagContext::necessaryflag ) );
        
    //     return output;
    //   }
    // }
  }

  /**
   * Contains all necessary information about a Template instantiation.
   * 
   * @author David K. Legg
   *
   */
  public static class Instance {
    public String instantiation;
    public Path triggerPath;
    public JsonNode triggerObject;
    
    public Instance(String instantiation, Path triggerPath, JsonNode triggerObject) {
      this.instantiation = instantiation;
      this.triggerPath   = triggerPath;
      this.triggerObject = triggerObject;
    }
  }
  
  /// Private inner classes
  
  /**
   * Represents a single piece of a Template.
   * Is either literal text or a Field
   * 
   * @author David K. Legg
   *
   */
  private static class Component {
    public boolean isField;
    public String content;
    
    /**
     * Constructs a literal text Component.
     * @param content The literal text to insert for this Component.
     * @see {@link Component#Component(String, boolean)}
     */
    public Component(String content) {
      this(content, false);
    }
    /**
     * Constructs a Component with given content, as either literal text or Field.
     * @param content The literal text to insert, or the name of corresponding Field
     * @param isField True if this Component represents a Field, and content should not be literal
     * @see {@link Component#Component(String)}
     */
    public Component(String content, boolean isField) {
      this.isField = isField;
      this.content = content;
    }

    /**
     * Converts this Component to a JSON object, using strict format
     * @param strict Whether to use strict (JSON object) or non-strict (mixed String/JSON object) format
     * @return a JSON object completely specifying this Component
     * @see {@link Component#toJSON(boolean)}
     * @see {@link Component#fromJSON(Object)}
     */
    public Object toJSON() {
      return toJSON(true);
    }
    /**
     * Converts this Component to a JSON object
     * If non-strict formatting is used and this Component is literal text, returns content String.
     * @param strict Whether to use strict (JSON object) or non-strict (mixed String/JSON object) format
     * @return a JSON object completely specifying this Component
     * @see {@link Component#toJSON()}
     * @see {@link Component#fromJSON(Object)}
     */
    public Object toJSON(boolean strict) {
      if (strict) {
        return new JSONObject()
            .put("_type", "Component")
            .put("isField", isField)
            .put("content", content);
      } else {
        if (isField) {
          return new JSONObject()
              .put("isField", isField)
              .put("content", content);
        } else {
          return content;
        }
      }
    }

    /**
     * Constructs a Component based on the given JSON object.
     * Will accept either strict or non-strict formats produced by toJSON.
     * @param jsonObj The JSON object to convert
     * @return The Component specified by the JSON
     * @throws S2KParseException if conversion fails for any reason
     * @see {@link Component#toJSON()}
     * @see {@link Component#toJSON(boolean)}
     */
    public static Component fromJSON(Object jsonObj) throws IllegalArgumentException {
      if (jsonObj instanceof String) {
        return new Component((String) jsonObj);
      } else if (jsonObj instanceof JSONObject) {
        JSONObject trueJsonObj = (JSONObject) jsonObj;
        
        if (trueJsonObj.optString("_type", "Component").equals("Component")) {
          throw new IllegalArgumentException("Could not parse JSON as a Component.");
        }
        
        return new Component(trueJsonObj.getString("content"),
                             trueJsonObj.optBoolean("isField", false));
      } else {
        throw new IllegalArgumentException("Could not parse JSON as a Component.");
      }
    }
  }

  /**
   * An extension to the Antlr4 Visitor, connects to Template grammar (body of templates).
   * 
   * @author David K. Legg
   *
   */
  public static class TemplateVisitor extends TemplateParserBaseVisitor<Optional<Template>> {
    @Override
    public Optional<Template> visitTemplate(TemplateParser.TemplateContext ctx) {
      Template output = new Template();
      
      output.name = Optional.ofNullable( ctx.name() )
          .map( NameContext::getText )
          .map( String::trim )
          .orElse("");

      for (ComponentContext component : ctx.component()) {
        if (component.field() != null) {
          // field component
          Field field = new Field(component.field().getText().replaceFirst(FIELD_PREFIX, ""));
          
//          output.putField(field);
          output.components.add( new Component(field.name, true) );
          
        } else {
          // general text component
          output.components.add( new Component(component.getText()) );
        }
      }
      
      return Optional.of(output);
    }
  }
  
  /**
   * An extension to the Antlr4 Visitor, connects to Translation grammar (includes field definitions).
   * 
   * @author David K. Legg
   *
   */
  public static class TemplatedefVisitor extends TranslationParserBaseVisitor<Optional<Template>> {
    @Override
    public Optional<Template> visitTemplatedef(TemplatedefContext ctx) {
      Optional<Template> temp;
      Template output;
      TemplateLexer lexer     = new TemplateLexer( CharStreams.fromString(ctx.template().getText()) );
      TemplateParser parser   = new TemplateParser( new CommonTokenStream(lexer) );
      TemplateVisitor visitor = new TemplateVisitor();
      temp = parser.template().accept(visitor);
      if (!temp.isPresent()) {
        // couldn't parse the template, so just fail early
        return Optional.empty();
      } else {
        output = temp.get();
      }
      
      if (ctx.templateoption() != null) {
        for (TemplateoptionContext templateoption : ctx.templateoption()) {
          if (templateoption.noduplicates() != null) {
            output.noDuplicates = true;
          }
        }
      }
      
      for (FielddefContext fielddef : ctx.fielddef()) {
        String fieldName = fielddef.fieldname().getText();
        Field field = new Field(fieldName);

        if (fielddef.fieldmodifiers() != null) {
          for (FieldflagContext fieldflag : fielddef.fieldmodifiers().fieldflag()) {
            if (fieldflag.triggerflag() != null) {
              field.setTrigger(true);
            } else if (fieldflag.requiredflag() != null) {
              field.setNecessary(true);
            } else if (fieldflag.keywordflag() != null) {
              field.setKeyword(true);
            } else if (fieldflag.rawflag() != null) {
              field.setRaw(true);
              if (fieldflag.rawflag().alternatesanitizer() != null) {
                field.setAlternateSanitizer( unquote( fieldflag.rawflag().alternatesanitizer().getText() ) );
              }
            } else if (fieldflag.multiflag() != null) {
              field.setMulti(true);
              if (fieldflag.multiflag().valuejoiner() != null) {
                field.setValueJoiner( unquote( fieldflag.multiflag().valuejoiner().getText() ) );
              }
            } else if (fieldflag.bodyflag() != null) {
              field.setBody(true);
              if (fieldflag.bodyflag().bodyjoiner() != null) {
                field.setBodyJoiner( unquote( fieldflag.bodyflag().bodyjoiner().getText() ) );
              }
              // find the indent on this field and use it if possible
              Component last = null;
              for (Component c : output.components) {
                if (c.isField && c.content.equals( field.name ) &&
                    last != null && !last.isField && last.content.trim().equals("")) {
                  field.setBodyIndent(last.content.replaceAll("\n", ""));
                  break;
                }
                last = c;
              }
            }
          }
        }
        
        if (fielddef.formatstring() != null) {
          field.setFormatString( unquote( fielddef.formatstring().getText() ) );
        }

        output.putField(field);

        if (fielddef.path() != null) {
          try {
            output.dataSource.put(field.name, Path.fromPathStr(fielddef.path().getText()));
          } catch (IllegalArgumentException e) {
            return Optional.empty();
          }
        } else if (!field.isBody) {
          return Optional.empty();
        }
      }
      
      return Optional.of(output);
      
    }

    private String unquote(String quotedString) {
      return StringEscapeUtils.unescapeJava( quotedString.replaceAll("^\"|\"$", "") );
    }
  }
}
