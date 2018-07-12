package gov.nasa.jpl.text2text;

import java.util.LinkedList;
import java.util.List;
//import java.util.AbstractMap;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

//import k.frontend.ASTOptions;
//import k.frontend.Frontend;
//import k.frontend.Model;

import gov.nasa.jpl.jsonPath2.*;

import static gov.nasa.jpl.jsonPath2.Utils.*;

/**
 * A collection of preprocessors that can be accessed by Translator.
 * 
 * @author David K. Legg
 *
 */
public class Preprocessors {
  private static int COUNTER = 0;
  
  /// Public methods
  
  public static JsonNode def(String str) {
    return JsonNode.readFrom(str);
  }
//  public static JsonNode kParser(String kString) {
//    Model model = Frontend.getModelFromString(kString);
//    Boolean oldUseJson1 = ASTOptions.useJson1();
//    ASTOptions.useJson1_$eq(false);
//    JSONObject jsonModel = model.toJson();
//    ASTOptions.useJson1_$eq(oldUseJson1);
//    return JsonNode.buildFrom(jsonModel);
//  }
  public static JsonNode jsonReflection(String str) {
    return JsonNode.buildFrom( innerReflection( JsonNode.readFrom(str) ) );
  }
  public static JsonNode mmsFlatten(String str) {
    List<JSONObject> elements = new LinkedList<>();
    innerMMSFlatten( JsonNode.readFrom(str), elements );
    return JsonNode.buildFrom( innerReflection( JsonNode.buildFrom(elements) ) );
  }
  
  public static Function<String,JsonNode> splitStrings(List<String> delimiters) {
    return str -> {
      JsonNode base = JsonNode.readFrom(str);
      return JsonNode.buildFrom( innerSplitStrings(base, delimiters) );
    };
  }
  
  public static JsonNode replaceLatex(String str) {
    return JsonNode.buildFrom( innerReplaceLatex( JsonNode.readFrom(str) ) );
  }
  public static JsonNode addCounter(String str) {
    return JsonNode.buildFrom( innerAddCounter( JsonNode.readFrom(str) ) );
  }
  
  /**
   * Combines two preprocessors into one.
   * @param first The first Preprocessor to apply
   * @param second The second Preprocessor to apply
   * @return A Preprocessor that acts like second(first( . ))
   */
  public static Function<String,JsonNode> compose(Function<String, JsonNode> first, Function<String, JsonNode> second) {
    return first.andThen( JsonNode::toString ).andThen( second );
  }
  
  /// Private helpers
  
  private static Object innerReflection(JsonNode model) {
    Function<JsonNode, String> getClassName = node -> node.as(ValueNode.class)
        .map(ValueNode::value)
        .map(Object::getClass)
        .map(Class::getSimpleName)
        .orElse(node.getClass().getSimpleName());
    
    Function<JsonNode, JSONObject> makeVal = node -> 
        new JSONObject()
            .put("type", getClassName.apply(node))
            .put("value", innerReflection(node));

    if (model instanceof ObjectNode) {
      JSONArray obj = new JSONArray();

      model.as(ObjectNode.class).get()
          .entries().stream()
          .forEach(consumeEntry((attr, val) -> {
        obj.put( makeVal.apply(val).put("attr", attr) );
      }));

      return obj;
    } else if (model instanceof ArrayNode) {
      JSONArray obj = new JSONArray();

      model.as(ArrayNode.class).get()
          .entries().stream()
          .forEach(consumeEntry((index, val) -> {
        obj.put( makeVal.apply(val).put("index", index) );
      }));

      return obj;

    } else {
      return model;
    }
  }
  private static Object innerMMSFlatten(JsonNode obj, List<JSONObject> elements) {
    if (obj instanceof ObjectNode) {
      JSONObject newObj = new JSONObject();
      obj.as(ObjectNode.class).get()
          .entries().stream()
          .map(onValue( val -> innerMMSFlatten(val, elements) ))
          .forEach(consumeEntry( newObj::put ));
      elements.add(newObj);
      return obj.as(ObjectNode.class)
          .flatMap( o -> o.get("id") )
          .flatMap( id -> id.as(ValueNode.class) )
          .map( ValueNode::value )
          .orElse(newObj);
      
    } else if (obj instanceof ArrayNode) {
      JSONArray output = new JSONArray();
      obj.as(ArrayNode.class).get()
          .values().stream()
          .map( val -> innerMMSFlatten(val, elements) )
          .forEach( output::put );
      return output;
      
    } else {
      return obj.as(ValueNode.class).map( v -> v.value() ).orElse(obj);
    }
  }
  private static Object innerSplitStrings(JsonNode obj, List<String> delimiters) {
    return Utils.firstPresent(
          () -> obj.as(ValueNode.class)
            .map( ValueNode::value )
            .filter( value -> value instanceof String )
            .map( Object::toString ) // actually a do-nothing, but a clean way to get String type
            .map( str -> {
              List<String> strings = Arrays.asList(str);
              for (String delim : delimiters) {
                strings = strings.stream()
                  .flatMap( s -> Arrays.asList( s.split(delim) ).stream() )
                  .filter( s -> !s.isEmpty() )
                  .collect( Collectors.toList() );
              }
  
              if (strings.size() == 1) {
                return strings.get(0);
              }
  
              JSONArray output  = new JSONArray();
              JSONArray current = output;
  
              for (String s : strings) {
                current.put(s);
                JSONArray next = new JSONArray();
                current.put(next);
                current = next;
              }
  
              return output;
            }),
            
          () -> obj.as(ObjectNode.class)
            .map( onode -> {
              JSONObject output = new JSONObject();
              onode.entries().stream()
                .map(onValue( val -> innerSplitStrings(val, delimiters) ))
                .forEach(consumeEntry( output::put ));
              return output;
            }),
            
          () -> obj.as(ArrayNode.class)
            .map( anode -> {
              JSONArray output = new JSONArray();
              anode.values().stream()
                .map( val -> innerSplitStrings(val, delimiters) )
                .forEach( output::put );
              return output;
            }),
            
          () -> obj.as(ValueNode.class).map( ValueNode::value )
        ).orElseGet( () -> obj.toJSON() );
  }
  private static Object innerReplaceLatex(JsonNode node) {
    Function<String,String> sanitizeString = str ->
      str
        .replaceAll("\\\\cdot", "*") // escaped twice, for java parser and regex parser
        .replaceAll("(?<!=)=(?!=)", "==") // replace single = with double ==
        .replaceAll("\\^", "**")
        .replaceAll("\\\\left(\\(|\\[)", "(")
        .replaceAll("\\\\right(\\)|\\])", ")")
      ;
    
    return Utils.firstPresent(
        () -> node.as(ValueNode.class)
          .map( ValueNode::asText )
          .map( sanitizeString ),
        () -> node.as(ObjectNode.class)
          .map( onode -> {
            JSONObject output = new JSONObject();
            onode.entries().stream()
              .map(onValue( Preprocessors::innerReplaceLatex ))
              .forEach(consumeEntry( output::put ));
            return output;
          }),
        () -> node.as(ArrayNode.class)
          .map( anode -> {
            JSONArray output = new JSONArray();
            anode.values().stream()
              .map( Preprocessors::innerReplaceLatex )
              .forEach( output::put );
            return output;
          })
        ).orElseGet( () -> node.toJSON() );
  }
  private static Object innerAddCounter(JsonNode node) {
    return Utils.firstPresent(
          () -> node.as(ObjectNode.class)
            .map( onode -> {
              JSONObject output = new JSONObject().put("__COUNTER", ++COUNTER);
              onode.entries().stream()
                .map(onValue( Preprocessors::innerAddCounter ))
                .forEach(consumeEntry( output::put ));
              return output;
            }),
          () -> node.as(ArrayNode.class)
            .map( anode -> {
              JSONArray output = new JSONArray();
              anode.values().stream()
                .map( Preprocessors::innerAddCounter )
                .forEach( output::put );
              return output;
            })
        ).orElseGet( () -> node.toJSON() ); // ValueNode.toJSON() handles itself nicely
  }
}
