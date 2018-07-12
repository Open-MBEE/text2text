package gov.nasa.jpl.text2text;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONObject;

import gov.nasa.jpl.jsonPath2.JsonNode;
import gov.nasa.jpl.jsonPath2.Utils;
import gov.nasa.jpl.jsonPath2.Utils.Logger;
import gov.nasa.jpl.text2text.Template.TemplatedefVisitor;
import gov.nasa.jpl.text2text.TranslationParser.*;

import static gov.nasa.jpl.jsonPath2.Utils.*;

/**
 * Collects a set of templates along with various plugins to define how translation will act.
 * 
 * @author David K. Legg
 *
 */
public class TranslationDescription extends LinkedHashMap<String, Template> {
  private static final long serialVersionUID = 8052745587121104868L;
  public static Logger defaultLogger;

  /// Public fields
  
  public Plugins plugins;
  
  /// Public methods
  
  public TranslationDescription() {
    super();
    plugins = defaultPlugins();
  }
  
  public JSONObject toJSON() {
    throw new UnsupportedOperationException("Not implemented yet.");
  }
  
  /**
   * Constructs a TranslationDescription from a template file, as a string.
   * 
   * @param templateFileString The String which has all template and field definitions in Template syntax.
   * @return The TranslationDescription with every parseable template added.
   * @throws S2KParseException If the string cannot be parsed for any reason.
   */
  public static TranslationDescription fromString(String templateFileString) throws IllegalArgumentException {
    try {
      TranslationLexer lexer = new TranslationLexer( CharStreams.fromString(templateFileString) );
      TranslationParser parser = new TranslationParser( new CommonTokenStream(lexer) );
      TemplateFileVisitor tfv = new TemplateFileVisitor();
      return tfv.visit( parser.templatefile() );
    } catch (Throwable e) {
      defaultLogger.error("Could not parse string as a TranslationDescription.");
      throw new IllegalArgumentException("Could not parse string as a TranslationDescription.", e);
    }
  }
  
  /**
   * Checks this object for errors and reports them. Also checks all Templates it contains.
   * @return A List of error messages, which is empty if there are no errors.
   */
  public List<String> errors() {
    List<String> output = new LinkedList<String>();
    
    if (plugins == null) output.add("No plugins are set on the translation description.");
    else {
      if (plugins.preprocessor == null) output.add("No preprocessor is set on the translation description.");
      if (plugins.sanitizer == null) output.add("No sanitize function is set on the translation description.");
      if (plugins.nativeFn  == null) output.add("No native function is set on the translation description.");
      if (plugins.libraries == null) output.add("Libraries were set to null");
    }
    
    for (Template t : this.values()) {
      output.addAll(t.errors());
    }
    
    return output;
  }
  
  /**
   * Adds template to this TranslationDescription
   * and sets template's logger to this TranslationDescription's logger.
   * @param template The template to add
   */
  public void add(Template template) {
    this.put(template.getName(), template);
  }

  /**
   * Adds template to this TranslationDescription
   * and sets template's logger to this TranslationDescription's logger.
   * @param name The name of template (using other keys may break things)
   * @param template The template to add
   */
  @Override
  public Template put(String name, Template template) {
    template.setLogger(plugins.logger);
    super.put(name, template);
    return template;
  }
  
  // TODO: perhaps flesh out the other put methods? not sure if the above override is enough
  
  public void setLogger(Logger newLogger) {
    this.plugins.logger = newLogger;
    for (Template t : this.values()) {
      t.setLogger(newLogger);
    }
  }
  
  public static class TemplateFileVisitor extends TranslationParserBaseVisitor<TranslationDescription> {
    @Override
    public TranslationDescription visitTemplatefile(TemplatefileContext ctx) {
      TemplatedefVisitor tv = new TemplatedefVisitor();
      TranslationDescription output = new TranslationDescription();
      output.plugins.logger = defaultLogger;
      for (OptionspecContext option : ctx.optionspec()) {
        List<String> optionValues = option.optionvalue().stream()
            .map( OptionvalueContext::getText )
            .map( s -> s.replaceAll("^\"|\"$", "") )
            .map( StringEscapeUtils::unescapeJava )
            .collect( Collectors.toList() );
        output.plugins.setOption(option.optionname().getText(), optionValues);
      }
      ctx.templatedef().stream()
        .map( template -> template.accept(tv) )
        .flatMap( optStream() )
        .forEach( template -> template.setTranslationDescription(output) );
      return output;
    }
  }


  /**
   * @return A Plugins object with some common functions and aliases added.
   */
  public static Plugins defaultPlugins() {
    Function<String, String> identSanitizer = s -> {
      s = s.replaceAll("[^a-zA-Z0-9]", "_");
      if (s.matches("^[0-9].*")) {
        s = "__" + s;
      }
      return s;
    };
    
    Function<String, String> unidentSanitizer = s -> {
      if (s.startsWith("__")) {
        s = s.substring(2);
      }
      return s;
    };
    
    Plugins plugins = new Plugins();
    plugins.addPreprocessor(Preprocessors::def, "default", "def", "none", "identity");
//    plugins.addPreprocessor(Preprocessors::kParser, "K parser", "kParser", "k");
    plugins.addPreprocessor(Preprocessors::jsonReflection, "json reflection", "jsonReflection", "reflection", "jsonReflect", "reflect");
    plugins.addPreprocessor(Preprocessors::mmsFlatten, "flatten", "mms", "mmsJson", "jsonMMS", "flattenMMS", "mmsFlatten");
    plugins.addPreprocessor(Preprocessors.splitStrings( Arrays.asList(";s",";e") ), "becosStrings", "becosStrs", "becosStr", "becos");
    plugins.addPreprocessor(Preprocessors::replaceLatex, "replace latex", "replaceLatex", "repLatex", "latex");
    plugins.addPreprocessor(Preprocessors::addCounter, "add counter", "addCounter", "counter", "count");
    
    plugins.addSanitizer(s -> s, "default", "def", "none", "identity");
    plugins.addSanitizer(identSanitizer, "identifier", "ident", "name");
    plugins.addSanitizer(unidentSanitizer, "undo identifier", "undoIdentifier", "unidentifier", "undo ident", "undoIdent", "undo name", "undoName", "unname");
    plugins.addSanitizer(StringEscapeUtils::escapeJava, "string escape", "stringEscape", "escape", "string", "escapeString", "escapeStr", "strEscape", "strEsc", "escStr", "str", "esc");
    plugins.addSanitizer(StringEscapeUtils::unescapeJava, "string unescape", "stringUnescape", "unescape", "unescapeString", "unescapeStr", "strUnescape", "strUnesc", "unesc");
    
    plugins.addNative(s -> Optional.empty(), "default", "def", "none", "identity");
    
    return plugins;
  }
  
  public static class Plugins {
    /// public constants
    public static final List<String> librariesOptions        = Arrays.asList("libraries", "lib", "libs");
    public static final List<String> preprocessorOptions     = Arrays.asList("preprocessor", "prep");
    public static final List<String> nativeFunctionOptions   = Arrays.asList("nativefunction", "nativefn", "native", "nat");
    public static final List<String> sanitizeFunctionOptions = Arrays.asList("sanitizefunction", "sanitizefn", "sanitizer", "sanitize", "san");

    // global defaults, used to set instance defaults when not given
    private static final Collection<JsonNode>       DEFAULT_LIBRARIES      = Collections.emptyList();
    private static final Function<String, JsonNode> DEFAULT_PREPROCESSOR   = JsonNode::readFrom;
    private static final Function<String, String>   DEFAULT_SANITIZER      = s -> s;
    private static final Function<String, Optional<String>> DEFAULT_NATIVE = s -> Optional.empty();
    
    /// Public members
    
    // instance defaults, used as return values when specific values can't be found
    public Collection<JsonNode> libraries;
    public Function<String, JsonNode> preprocessor;
    public Function<String, String>   sanitizer;
    public Function<String, Optional<String>> nativeFn;
    public Logger logger;
    
    /// Private members
    
    private Map<Function<?,?>, String> canonicalNames = new LinkedHashMap<>();
    private Map<String, Function<String, JsonNode>> preprocessors   = new LinkedHashMap<>();
    private Map<String, Function<String, String>>   sanitizers      = new LinkedHashMap<>();
    private Map<String, Function<String, Optional<String>>> natives = new LinkedHashMap<>();
    
    
    /// Public methods
    
    public Plugins() {
      this(null);
    }
    public Plugins(Logger logger) {
      this(null, null, null, null, null);
    }
    public Plugins(Collection<JsonNode> libraries,
                   Function<String, JsonNode> preprocessor,
                   Function<String, String>   sanitizer,
                   Function<String, Optional<String>> nativeFn,
                   Logger logger) {
      this.libraries    = (libraries    == null ? DEFAULT_LIBRARIES    : libraries   );
      this.preprocessor = (preprocessor == null ? DEFAULT_PREPROCESSOR : preprocessor);
      this.sanitizer    = (sanitizer    == null ? DEFAULT_SANITIZER    : sanitizer   );
      this.nativeFn     = (nativeFn     == null ? DEFAULT_NATIVE       : nativeFn    );
      this.logger = (logger == null ? Logger.SILENT_LOGGER : logger);
    }
    
    public Function<String, JsonNode> getPreprocessor(String name) {
      Function<String, JsonNode> output = preprocessors.get(name.toLowerCase());
      if (output == null) {
        logger.warning("Could not find preprocessor " + name + ". Using default.");
        output = DEFAULT_PREPROCESSOR;
      } 
      return output;
    }
    public Function<String, String> getSanitizer(String name) {
      List<String> names = Arrays.asList( name.split("\\+") );
      return names.stream()
          .map( String::toLowerCase )
          .map( splitStream(sanitizers::get) )
          .peek(consumeEntry( (fn, nm) -> {
            if (fn == null) logger.warning("Could not find sanitizer " + nm + ". Ignoring.");
          }))
          .map( Entry::getKey )
          .filter( x -> x != null )
          .reduce( Function::andThen )
          .orElseGet( () -> {
            logger.warning("Could not find sanitizer " + name + ". Using default.");
            return DEFAULT_SANITIZER;
          });
      
    }
    public Function<String, Optional<String>> getNative(String name) {
      Function<String, Optional<String>> output = natives.get(name.toLowerCase());
      if (output == null) {
        logger.warning("Could not find native function " + name + ". Using default.");
        output = DEFAULT_NATIVE;
      } 
      return output;
    }
    
    public void addPreprocessor(Function<String, JsonNode> preprocessor, String canonicalName, String... aliases) {
      preprocessors.put(canonicalName.toLowerCase(), preprocessor);
      for (String alias : aliases) {
        preprocessors.put(alias.toLowerCase(), preprocessor);
      }
      canonicalNames.put(preprocessor, canonicalName);
    }
    public void addSanitizer(Function<String, String> sanitizer, String canonicalName, String... aliases) {
      sanitizers.put(canonicalName.toLowerCase(), sanitizer);
      for (String alias : aliases) {
        sanitizers.put(alias.toLowerCase(), sanitizer);
      }
      canonicalNames.put(sanitizer, canonicalName);
    }
    public void addNative(Function<String, Optional<String>> nativeFn, String canonicalName, String... aliases) {
      natives.put(canonicalName.toLowerCase(), nativeFn);
      for (String alias : aliases) {
        natives.put(alias.toLowerCase(), nativeFn);
      }
      canonicalNames.put(nativeFn, canonicalName);
    }
    
    public String getCanonicalName(Function<?,?> fn) {
      return canonicalNames.getOrDefault(fn, "function");
    }
    
    public Function<String, Optional<String>> buildKeywordNative(Collection<String> keywords) {
      return str -> keywords.stream().filter( str::equalsIgnoreCase ).findAny();
    }
  
    /**
     * Sets the option described by name, as best as possible.
     * What is allowable for the inputs param depends on the option being set.
     * For preprocessor or sanitizer, one of a short list of recognized functions should be given.
     * For libraries, a file name should be given.
     * For native, a list of keywords should be given
     * 
     * @param name The name of the option, some form of preprocessor, sanitizer, etc.
     * @param inputs An allowable input for this option
     */
    public void setOption(String name, List<String> inputs) {
      name = name.toLowerCase();
      if (inputs.size() < 1) {
        logger.warning("Ignoring option %s because no inputs were given for it.", name);
        return;
      }
      if (librariesOptions.contains(name)) {
          libraries = inputs.stream()
            .map(splitStream(optOnError( filename -> new FileInputStream(filename) )))
            .peek(consumeEntry( (readOpt, filename) -> {
              if (readOpt.isPresent()) {
                logger.verbose("Successfully read library %s.", filename);
              } else {
                logger.warning("Failed to read library %s.", filename);
              }
            }))
            .map( Entry::getKey )
            .flatMap( optStream() )
            .map(optOnError( Utils::streamToString ) )
            .flatMap( optStream() )
            .map( JsonNode::readFrom )
            .collect( Collectors.toList() );
      } else if (preprocessorOptions.contains(name)) {
        preprocessor = inputs.stream()
            .map( this::getPreprocessor )
            .reduce( Preprocessors::compose )
            .orElseGet( () -> {
              logger.warning("Could not recognize preprocessors. Using default instead.");
              return DEFAULT_PREPROCESSOR;
            });
      } else if (sanitizeFunctionOptions.contains(name)) {
        if (inputs.size() > 1) {
          logger.warning("Ignoring extra options for %s.", name);
        }
        sanitizer = getSanitizer(inputs.get(0));
      } else if (nativeFunctionOptions.contains(name)) {
        if (inputs.size() > 1) {
          nativeFn = buildKeywordNative(inputs);
        } else {
          nativeFn = getNative(name);
        }
      } else {
        logger.warning("Could not recognize option %s.", name);
      }
    }
  }
}
