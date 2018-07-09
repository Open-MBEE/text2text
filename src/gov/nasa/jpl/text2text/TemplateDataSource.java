package gov.nasa.jpl.text2text;

import java.util.LinkedHashMap;
//import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import gov.nasa.jpl.jsonPath2.Path;

/**
 * Links the fields of a template with the paths that retrieve information for them.
 * 
 * @author David K. Legg
 *
 */
public class TemplateDataSource extends LinkedHashMap<String, Path> {
  private static final long serialVersionUID = -5686940214832612136L;


  /**
   * Converts to JSON object, using strict format
   * @return a JSON object, completely specifying the TemplateDataSource
   */
  public JSONObject toJSON() {
    return toJSON(true);
  }
  /**
   * Converts to JSON object
   * If non-strict format is used, resulting object will be more compact, but less robust to future change.
   * @param strict Whether to use strict (all JSON) or non-strict (mixed JSON/toString)
   * @return a JSON object completely specifying the TemplateDataSource
   */
  public JSONObject toJSON(boolean strict) {
    JSONObject data = new JSONObject()
        .put("_type", "TemplateDataSource");
    this.forEach( (key, path) -> {
      data.put(key, path.toJSON());
    });
    return data;
  }

  /**
   * Constructs a TemplateDataSource based on the given JSON object.
   * Will accept either strict or non-strict formats produced by toJSON, or some mixes of them.
   * @param jsonObj The JSON object to convert
   * @return The TemplateDataSource specified by the JSON
   * @throws S2KParseException if conversion fails for any reason
   */
  public static TemplateDataSource fromJSON(JSONObject jsonObj) throws IllegalArgumentException {
    try {
      if (!jsonObj.optString("_type", "TemplateDataSource").equals("TemplateDataSource")) {
        throw new IllegalArgumentException("jsonObj does not represent a TemplateDataSource.");
      }
  
      TemplateDataSource output = new TemplateDataSource();
      
      @SuppressWarnings("unchecked")
      Set<String> keySet = jsonObj.keySet();
      keySet.stream()
          .filter( key -> !key.equals("_type") && !key.equals("parentTemplate") )
          .forEach( key -> {
            try {
              output.put(key, Path.fromJSON(jsonObj.get(key)));
            } catch (IllegalArgumentException | JSONException e) {
              // silently ignore exceptions, but log the exception
              e.printStackTrace();
            }
          });
      return output;
    } catch (JSONException e) {
      throw new IllegalArgumentException("Could not parse JSON as a TemplateDataSource.", e);
    }
  }
}
