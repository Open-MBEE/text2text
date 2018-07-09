package gov.nasa.jpl.text2text;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Stream;

/**
 * A map from a single key to a collection of values.
 * 
 * @author David K. Legg
 *
 * @param <K> Key type
 * @param <V> Value type
 */
public class Registrar<K,V> extends LinkedHashMap<K,Collection<V>> {
  private static final long serialVersionUID = -6933635496581222679L;

  /**
   * Constructs a Registrar from a Map of similar underlying structure.
   * @param map A "raw" registrar, with the same underlying data structure as a registrar
   * @return A true Registrar, containing the same data as map, with order preserved if using ordered collections.
   */
  public static <K,V> Registrar<K,V> fromMap(Map<K,Collection<V>> map) {
    Registrar<K,V> output = new Registrar<K,V>();
    map.forEach( output::registerAll );
    return output;
  }
  
  /**
   * Merges this Registrar with another, by appending the elements of other to this.
   * Does not modify this nor other
   * @param other
   * @return A new Registrar, whose keys are the union of this Registrar's and other's keys,
   *         and whose values are the concatenation of corresponding collections
   */
  public Registrar<K,V> merge(Registrar<K,V> other) {
    Registrar<K,V> output = new Registrar<K,V>();
    this .forEach( output::registerAll );
    other.forEach( output::registerAll );
    return output;
  }
  
  /**
   * Appends value to the collection for key
   * @param key The key corresponding to the Collection in this Registrar that will be modified
   * @param value The value to be added to that Collection
   */
  public void register(K key, V value) {
    if (!this.containsKey(key)) {
      addKey(key);
    }
    this.get(key).add(value);
  }
  
  /**
   * Register an entire collection for a given key.
   * @param key The key corresponding to the Collection in this Registrar that will be modified
   * @param values The collection of values to be added to that Collection
   */
  public void registerAll(K key, Collection<V> values) {
    values.forEach( value -> this.register(key, value) );
  }
  
  /**
   * Get the Collection corresponding to key
   * @param key The key, of type <code>K</code>, corresponding to the desired Collection in this Registrar.
   * @return The Collection in this Registrar for key, or a new Collection which is also registered if key isn't present
   * @throws ClassCastException if key is not of type <code>K</code>
   */
  @SuppressWarnings("unchecked")
  @Override
  public Collection<V> get(Object key) {
    if (!this.containsKey(key)) {
      addKey((K) key);
    }
    return super.get(key);
  }
  
  /**
   * Generates a stream of key/value pairs.
   * @return A Stream of entries from keys to single values in the corresponding Collection.
   */
  public Stream<Map.Entry<K,V>> pairingStream() {
    return this.entrySet().stream().flatMap( entry ->
        entry.getValue().stream().map( value ->
            new AbstractMap.SimpleEntry<K, V>(entry.getKey(), value) ));
  }
  
  /// Private helpers
  
  /**
   * Set a new collection for key
   * @param key The type-specific key to be added to this registrar
   */
  private void addKey(K key) {
    this.put(key, new LinkedList<V>());
  }
}
