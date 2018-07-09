package gov.nasa.jpl.text2text;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;

import gov.nasa.jpl.jsonPath2.JsonNode;

public class TemplateMatch extends LinkedHashMap<String, List<JsonNode>> {
  private static final long serialVersionUID = -1187773882559313226L;

  /// Private static constants
  
  private static final String PARENT_REFERENCE_NAME = "PARENT_REFERENCE";
  
  /// Private fields
  
  // holds other information about this match
  private Optional<Template> template      = Optional.empty();
  private Optional<JsonNode> triggerObject = Optional.empty();
  private Optional<String>   instance      = Optional.empty();
  
  /// Public methods
  
  public TemplateMatch() {
    super();
  }
  public TemplateMatch(Template template) {
    super();
    this.setTemplate(template);
  }
  
//  public static MatchRegistrar fromMatcher(Matcher matcher, Template referenceTemplate) {
//    MatchRegistrar output  = new MatchRegistrar();
//    TemplateMatch newMatch = new TemplateMatch();
//    newMatch.setTemplate(referenceTemplate);
//    
//    for (Field field : referenceTemplate.getFields()) {
//      if (field.isBody) {
//        for (Template template : referenceTemplate.getAllTemplates()) {
//          template.matchToTarget(matcher.group(field.name)).forEach( (templateName, matches) -> {
//                Template childTemplate = template.asChildOf(referenceTemplate);
//                matches.forEach( match -> {
//                  // make top-level returned matches reference this match as their parent
//                  match.putIfAbsent(PARENT_REFERENCE_NAME, JsonNode.buildFrom(matcher.group(referenceTemplate.getTriggerName())));
//                  output.register(childTemplate, match);
//                });
//              });
//        }
//      } else {
//        newMatch.put(field.name, JsonNode.buildFrom(matcher.group(field.name)));
//      }
//    }
//    
//    output.register(referenceTemplate, newMatch);
//    return output;
//  }
  
  public Optional<Template> getTemplate() {
    return template;
  }
  
  public Optional<JsonNode> getParentReference() {
    return this.getFirst(PARENT_REFERENCE_NAME);
  }
  
  public Optional<JsonNode> getTriggerValue() {
    return triggerObject;
  }
  
  public Optional<String> getInstance() {
    return instance;
  }

  /**
   * Returns the first element of the Collection mapped to by key.
   * @param key The key of the Collection to look up.
   * @return An Optional of the first element in the mapped Collection, or an empty Optional if no such element exists.
   */
  public Optional<JsonNode> getFirst(String key) {
    return this.getOrDefault(key, Collections.emptyList()).stream().findFirst();
  }
  
  public void setTemplate(Template template) {
    this.template = Optional.ofNullable(template);
  }

  public void setTriggerValue(JsonNode object) {
    this.triggerObject = Optional.ofNullable(object);
  }
  
  public void setInstance(String instance) {
    this.instance = Optional.ofNullable(instance);
  }
  
  public JSONObject toJSON() {
    return new JSONObject(this).put("_type", "Match");
  }
}