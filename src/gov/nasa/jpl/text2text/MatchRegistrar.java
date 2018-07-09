package gov.nasa.jpl.text2text;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import gov.nasa.jpl.jsonPath2.JsonNode;

import static gov.nasa.jpl.jsonPath2.Utils.*;

/**
 * A Registrar for collecting the matches for Templates.
 * 
 * @author David K. Legg
 *
 */
public class MatchRegistrar extends Registrar<Template,TemplateMatch> {
  private static final long serialVersionUID = -6657230284428675596L;
  
  /**
   * Returns the templates and their matches, ordered so that instantiation dependencies are satisfied.
   * In particular, matches will always be output before the match they reference as their parent, if possible.
   * If a circular dependency is encountered, the affected matches are output in arbitrary order.
   * @return A List of TeplatePairs satisfying the above ordering principle, if possible.
   */
  public List<TemplatePair> instantiationOrder() {
    return instantiationOrder( Logger.SILENT_LOGGER );
  }
  public List<TemplatePair> instantiationOrder(Logger logger) {
    // Note: this is essentially a topological sort.
    // We use the Pairs as nodes, designated by their names, and compute edges as follows:
    // We define the source to be that templateMatch's name (the value of the trigger field)
    // We define the destination to be the parent reference, if any, of the templateMatch
    
    logger.verbose("Sorting instances...");
    
    List<TemplatePair> output = new LinkedList<TemplatePair>();
    List<TemplatePair> needToAdd = this.pairingStream()
        .map( TemplatePair::new )
        .collect( Collectors.toList() );
    
    List<TemplatePair> nonRecursives = needToAdd.stream()
        .filter( tp -> !tp.template.isRecursive() )
        .collect( Collectors.toList() );
    output.addAll(nonRecursives);
    needToAdd.removeAll(nonRecursives);
    logger.verbose(String.format("%d non-recursive templates added first.", nonRecursives.size()));

    Supplier<Set<JsonNode>> makeParentReferences = () -> needToAdd.stream()
        .map( innerPair -> innerPair.templateMatch.getParentReference() )
        .flatMap( optStream() )
        .collect( Collectors.toSet() );
    
    Set<JsonNode> parentReferencesLeft = makeParentReferences.get();
    
    // define a pair to be a source if no pair yet to be added refers to this pair as its parent.
    // that is to say, we can add a node iff all the nodes that call this their parent have been added prior.
    Predicate<TemplatePair> isSourceNode = pair -> pair.templateMatch.getTriggerValue()
        .map( value -> !parentReferencesLeft.contains(value) )
        .orElse(true);
      
    while (!needToAdd.isEmpty()) {
      logger.verbose(String.format("Need to add %d instances.", needToAdd.size()));
      
      // strip all the source nodes off the graph, and move them to the output
      List<TemplatePair> sources = needToAdd.stream()
        .filter( isSourceNode )
        .collect( Collectors.toList() );
      
      // just in case there are circular dependencies, for whatever reason:
      if (sources.isEmpty()) {
        logger.warning("Circular template dependency encountered.");
        logger.verbose(String.format("Adding all %d remaining instances.", needToAdd.size()));
        output.addAll(needToAdd);
        break; // just lump all of these together, since we can no longer distinguish between them
      }
      logger.verbose(String.format("Adding %d instances...", sources.size()));
      
      output.addAll( sources );
      needToAdd.removeAll( sources );
      parentReferencesLeft.clear();
      parentReferencesLeft.addAll( makeParentReferences.get() );
    }
    
    logger.verbose("Finished sorting instances.");
    
    return output;
  }
  
  /// Public sub-classes
  
  /**
   * Groups a template and a match for that template.
   * 
   * @author David K. Legg
   *
   */
  public static class TemplatePair {
    public Template template;
    public TemplateMatch templateMatch;
    
    /**
     * Constructs a TemplatePair based on a Map.Entry of the same structure.
     * @param entry A Map.Entry grouping a template and a match for that template
     * @see {@link TemplatePair#TemplatePair(Template, TemplateMatch)}
     */
    private TemplatePair(Map.Entry<Template, TemplateMatch> entry) {
      this(entry.getKey(), entry.getValue());
    }
    
    /**
     * Constructs a TemplatePair grouping a given template and match together
     * @param template
     * @param templateMatch
     * @see {@link TemplatePair#TemplatePair(java.util.Map.Entry)}
     */
    public TemplatePair(Template template, TemplateMatch templateMatch) {
      this.template = template;
      this.templateMatch = templateMatch;
    }
  }
}
