// --== CS400 Project One File Header ==--
// Name: Kyle Momanyi
// CSL Username: momanyi
// Email: kmomanyi@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader: could not get setting back to adding param in comment header automatically

import java.util.NoSuchElementException;
import java.util.LinkedList;

/*
 * Class that creates hash table map
 */
public class HashtableMap<KeyValue, ValueType> implements MapADT<KeyValue, ValueType> {
  protected int capacity; // capacity of hash table
  protected int size;
  protected LinkedList<NodeValue<KeyValue, ValueType>>[] nodes;


  /*
   * Constructor with input capcity
   */
  @SuppressWarnings("unchecked")
  public HashtableMap(int capacity) {
    this.capacity = capacity;
    this.size = 0;
    nodes = new LinkedList[capacity];
  }

  /*
   * constructor with default capacity
   */
  @SuppressWarnings("unchecked")
  public HashtableMap() {
    this.capacity = 20;// with default capacity = 20
    this.size = 0;
    nodes = new LinkedList[20];
  }

  /*
   * Helper method to get the hashIndex of input key
   */
  private int hashIndex(KeyValue key) {
    return Math.abs(key.hashCode() % this.capacity);
  }

  /*
   * helper method to get the load factor on the hashtable
   */
  private double loadFactor() {
    return ((double) size() / (double) capacity);
  }

  /*
   * helper method to add to the hashtable capcity if loadlactor is reached
   */
  private void addCap() {
    LinkedList<NodeValue<KeyValue, ValueType>>[] temp = this.nodes;
    this.capacity = this.capacity * 2;
    clear();

    for (int i = 0; i < temp.length - 1; i++) {
      if (temp[i] != null) {
        LinkedList<NodeValue<KeyValue, ValueType>> list = temp[i];
        for (int j = 0; j < list.size(); j++) {
          put(list.get(j).getKey(), list.get(j).getValue());
        }
      }
    }

  }

  /*
   * returns size of the hashtable
   */
  @Override
  public int size() {
    return this.size;
  }


  /*
   * method to clear the hashtable
   */
  @SuppressWarnings("unchecked")
  @Override
  public void clear() {
    nodes = new LinkedList[capacity];
    size = 0;
  }


  /*
   * method to get a vzlue based on input key from hashtable
   */
  @Override
  public ValueType get(KeyValue key) throws NoSuchElementException {
    if (!containsKey(key))
      throw new NoSuchElementException("Key DNE");
    int index = hashIndex(key);


    for (int i = 0; i < nodes[index].size(); i++) {
      if (nodes[index].get(i).getKey().equals(key))
        return (ValueType) nodes[index].get(i).getValue();
    }

    throw new NoSuchElementException("NO MATCH");
  }


  /*
   * method returns true if the key exists in hashtable false otherwise
   */
  @Override
  public boolean containsKey(KeyValue key) {
    if (key == null) {
      return false;
    }

    if (nodes[hashIndex(key)] == null) {
      return false;
    }

    LinkedList<NodeValue<KeyValue, ValueType>> list = this.nodes[hashIndex(key)];
    for (int i = 0; i < list.size(); i++) {
      if (key.equals(list.get(i).getKey())) {
        return true;
      }
    }

    return false;
  }


  /**
   * method to remove and return value from the hashtable based on input key
   */
  @SuppressWarnings("unchecked")
  @Override
  public ValueType remove(KeyValue key) {
    if (key == null)
      return null;
    int index = hashIndex(key);
    if (key == null || this.nodes[index] == null) {
      return null;
    }
    ValueType val = null;
    for (int i = 0; i < nodes[index].size(); i++) {
      if (nodes[index].get(i).getKey().equals(key))
        val = (ValueType) nodes[index].get(i).getKey();
      nodes[index].remove(i);
    }
    return val;
  }

  /*
   * This method adds a key and value to the hastable
   */
  @Override
  public boolean put(KeyValue key, ValueType value) {
    if (key.equals(null)) {
      return false;
    }
    if (containsKey(key)) {
      return false;
    }

    int index = hashIndex(key);

    if (nodes[index] == null) {
      if (loadFactor() >= .75) {
        addCap();
      }
      nodes[index] = new LinkedList<NodeValue<KeyValue, ValueType>>();
//      LinkedList<NodeValue<KeyValue, ValueType>> list = this.nodes[index];
      nodes[index].add(new NodeValue<>(key, value));
      size++;
      return true;
    }

    else if (nodes[index] != null) {
      if (loadFactor() >= .75) {
        addCap();
      }
//      LinkedList<NodeValue<KeyValue, ValueType>> list = this.nodes[index];
      nodes[index].add(new NodeValue<>(key, value));
      size++;
      return true;
    }
    return false;
  }
}
