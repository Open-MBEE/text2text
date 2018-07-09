package gov.nasa.jpl.text2text;

import gov.nasa.jpl.jsonPath2.JsonNode;
import gov.nasa.jpl.jsonPath2.ObjectNode;

/**
 * <p>
 * A Registrar for collecting instantiations of Templates.
 * The key is the instance's parent's instance name, and the value is
 * a TemplateMatch with a present instance member, representing the child instance.
 * </p>
 * 
 * @author David K. Legg
 *
 */
public class InstantiationRegistrar extends Registrar< JsonNode, TemplateMatch > {
  private static final long serialVersionUID = 4353874927766182041L;
  private static final JsonNode TOP_LEVEL_REFERENCE = new ObjectNode(); // an abstract, guaranteed unique node
  
  /**
   * Returns the key used for instances with no parent reference
   * @return The key for instances with no parent reference
   */
  public JsonNode getTopLevelReference() {
    return TOP_LEVEL_REFERENCE;
  }
  
  public void register(TemplateMatch templateMatch) {
    register(templateMatch.getParentReference().orElse(TOP_LEVEL_REFERENCE), templateMatch);
  }
}
