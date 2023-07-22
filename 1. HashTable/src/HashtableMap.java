// --== CS400 Project One File Header ==--
// Name: <Luke Erickson>
// CSL Username: <lerickson>
// Email: <lerickson7@wisc.edu>
// Lecture #: <004 @4:00pm>
// Notes to Grader: <any optional extra notes to your grader>

import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * Creates a Hashtable that takes KeyValuePairs, hashes them, and stores them in an array
 * 
 * @author lukee
 *
 * @param <KeyType>
 * @param <ValueType>
 */
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  protected LinkedList<KeyValuePair>[] array;
  private int size;
  private int capacity;

  /**
   * Basic constructor w/ capacity as a parameter
   * 
   * @param capacity length of the hashtable array
   */
  @SuppressWarnings("unchecked")
  public HashtableMap(int capacity) {
    this.capacity = capacity;
    array = (LinkedList<KeyValuePair>[]) new LinkedList[capacity];
    size = 0;
  }

  /**
   * Basic constructor that sets capacity to 20
   */
  @SuppressWarnings("unchecked")
  public HashtableMap() {
    this.capacity = 20;
    array = (LinkedList<KeyValuePair>[]) new LinkedList[20];
    size = 0;
  }

  /**
   * Attempts to add a KeyValuePair to our array
   * 
   * @param key   to add to array
   * @param value to stored w/ this key
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    // if the key is null, return false
    if (key == null)
      return false;
    // if the key is already in the array, return false
    if (containsKey(key))
      return false;
    // if the given index in the array is empty, add the new KeyValuePair
    if (array[hashHelper(key)] == null) {
      array[hashHelper(key)] = new LinkedList<KeyValuePair>();
      array[hashHelper(key)].add(new KeyValuePair(key, value));
      size++;
      loadFactorHelper();
      return true;
    }
    // if the given index is not empty, add the new KeyValuePair using chaining
    if (array[hashHelper(key)] != null) {
      array[hashHelper(key)].add(new KeyValuePair(key, value));
      size++;
      loadFactorHelper();
      return true;
    }
    return false;
  }

  /**
   * Get a ValueType from our array given its corresponding key
   * 
   * @param key to search for in the array
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    // search through each linked list in array
    for (LinkedList<KeyValuePair> i : array)
      if (i != null)
        // search through each KeyValuePair in each linked list
        for (KeyValuePair j : i)
          if (j.getK().equals(key))
            return (ValueType) j.getV();
    throw new NoSuchElementException();
  }

  /**
   * @return how many elements are in our array
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * @return the length of our array
   */
  public int getCapacity() {
    return capacity;
  }

  /**
   * @return true if a given key is in our array
   */
  @Override
  public boolean containsKey(KeyType key) {
    // search through each linked list in array
    for (LinkedList<KeyValuePair> i : array)
      if (i != null)
        // search through each KeyValuePair in each linked list
        for (KeyValuePair j : i)
          if (j.getK().equals(key))
            return true;

    return false;
  }

  /**
   * Removes a KeyValue pair from our array
   * 
   * @return ValueType that was removed
   */
  @Override
  public ValueType remove(KeyType key) {
    // search through each linked list in array
    for (LinkedList<KeyValuePair> i : array)
      if (i != null) {
        // search through each KeyValuePair in each linked list
        for (KeyValuePair j : i)
          if (j.getK().equals(key)) {
            // remove the given key and then return it
            ValueType k = (ValueType) j.getV();
            i.remove(j);
            size--;
            return k;
          }
      }
    // return null if the key isn't in the array
    return null;

  }

  /**
   * Clears the array of all data and sets its size to 0
   */
  @Override
  public void clear() {
    array = (LinkedList<KeyValuePair>[]) new LinkedList[this.capacity];
    size = 0;
  }

  /**
   * Hashes a given key
   * 
   * @param key to be hashed
   * @return the index at which this key should be stored in our array
   */
  private int hashHelper(KeyType key) {
    return Math.abs(key.hashCode() % capacity);
  }

  /**
   * checks if the loadFactor is greater than .75 and then resizes and rehashes the table
   * accordingly
   */
  private void loadFactorHelper() {
    double loadFactor = (double) size / (double) capacity;
    if (loadFactor >= 0.75) {

      // create a temporary array storing the contents of the old array
      LinkedList<KeyValuePair>[] tempArray = array;
      // set array to be a new array twice the size
      array = (LinkedList<KeyValuePair>[]) new LinkedList[capacity * 2];

      capacity *= 2;
      size = 0;

      // put each element from the temporary array into the new array
      for (LinkedList<KeyValuePair> i : tempArray)
        if (i != null)
          for (KeyValuePair j : i)
            put((KeyType) j.getK(), (ValueType) j.getV());

    }
  }

}
