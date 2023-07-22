// --== CS400 Project One File Header ==--
// Name: Kyle Momanyi
// CSL Username: momanyi
// Email: kmomanyi@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader: could not get setting back to adding param in comment header automatically

import java.util.List;
import java.util.NoSuchElementException;
import java.util.LinkedList;
import java.util.Collections;

public class HashTableSortedSets<KeyType, ValueType extends Comparable<ValueType>> 
extends HashtableMap<KeyType, List<ValueType>> 
implements IHashTableSortedSets<KeyType, ValueType> {

  
  
/*
 * Constructor to build a HashTableSortedSet with a specified capacity
 */
  public HashTableSortedSets(int capacity) {
    super(capacity);
  }
  
  /*
   * Constructor to build a HashTableSortedSet with default capacity of 20
   */
  public HashTableSortedSets() {
    super(10000); //default capacity = 20
  }
  
  /**
   * {@inheritDoc}
   * This method overrides the MapADT put method and puts add and key and list of values 
   * to the hashtable and returns true if successful and false if unsuccesssful
   */
  @Override
  public boolean put(KeyType key, List<ValueType> value) {
    if (key.equals(null)) {
      return false;
    }
    if (containsKey(key)) {
      return false;
    }

    int index = hashIndex(key);

    if (nodes[index] == null) {
     
      nodes[index] = new LinkedList<NodeValue<KeyType, List<ValueType>>>();
//      LinkedList<NodeValue<KeyType, List<ValueType>>> list = this.nodes[index];
      nodes[index].add(new NodeValue<>(key, value));
      size++;
      if (loadFactor() >= .75) {
        addCap();
      }
      return true;
    }

    else if (nodes[index] != null) {
     
//      LinkedList<NodeValue<KeyType, List<ValueType>>> list = this.nodes[index];
      nodes[index].add(new NodeValue<>(key, value));
      size++;
      if (loadFactor() >= .75) {
        addCap();
      }
      return true;
    }
    return false;
  }

  /**
   * This method overrides the MapADT get method that returns the List of values in the 
   * hashtable based on the key sent to the method 
   */
  @Override
  public List<ValueType> get(KeyType key) throws NoSuchElementException {
    if (!containsKey(key))
      throw new NoSuchElementException("Key DNE");
    int index = hashIndex(key);


    for (int i = 0; i < nodes[index].size(); i++) {
      if (nodes[index].get(i).getKey().equals(key))
        return (List<ValueType>) nodes[index].get(i).getValue();
    }

    throw new NoSuchElementException("NO MATCH");
  }

  /**
   * This method overrides the MapADT remove method which returns and removes and List of 
   * values from the hashtable and the specific key in the hashtable 
   */
  @SuppressWarnings("unchecked")
  @Override
  public List<ValueType> remove(KeyType key) {
    if (key == null)
      return null;
    int index = hashIndex(key);
    if (key == null || this.nodes[index] == null) {
      return null;
    }
    List<ValueType> val = null;
    for (int i = 0; i < nodes[index].size(); i++) {
      if (nodes[index].get(i).getKey().equals(key))
        val =  (List<ValueType>) nodes[index].get(i).getValue();
      nodes[index].remove(i);
    }
    return val;     
  }

  /**
   * This method is new and adds a value to the list that exists at a specific index in the 
   * hashtable in ascending order
   */
  @Override
  public void add(KeyType key, ValueType value) {
    
    int index = hashIndex(key);
    
    if (containsKey(key)) {
      for(int i = 0; i < nodes[index].size(); i++) {
        if(nodes[index].get(i).getKey() == key) {
          appendList(nodes[index].get(i).getValue(), value);
          if (loadFactor() >= .75) {
            addCap();
          }
        }
      }
    }

    else {
      nodes[index] = new LinkedList<NodeValue<KeyType, List<ValueType>>>();
      LinkedList<ValueType> toAdd = new LinkedList<ValueType>();
      toAdd.add(value);
      nodes[index].add(new NodeValue<>(key, toAdd));
    }
    if (loadFactor() >= .75) {
      addCap();
    }
    size++;
  }
  
  /**
   * This method returns true if the key is in the hashtable and false otherwise
   */
  @Override
  public boolean containsKey(KeyType key) {
    if (key == null) {
      return false;
    }

    if (nodes[hashIndex(key)] == null) {
      return false;
    }

    LinkedList<NodeValue<KeyType, List<ValueType>>> list = this.nodes[hashIndex(key)];
    for (int i = 0; i < list.size(); i++) {
      if (key.equals(list.get(i).getKey())) {
        return true;
      }
    }

    return false;
  }
  
  /*
   * Helper method to get the hashIndex of input key
   */
  private int hashIndex(KeyType key) {
    return Math.abs(key.hashCode() % capacity);
  }
  
  /*
   * helper method to get the load factor on the hashtable
   */
  private double loadFactor() {
    return ((double) size / (double) capacity);
  }
  
  /*
   * helper method to add to the hashtable capcity if loadfactor is reached
   */
  private void addCap() {
    LinkedList<NodeValue<KeyType, List<ValueType>>>[] temp = this.nodes;
    capacity = this.capacity * 2;
    clear();

    for (int i = 0; i < temp.length - 1; i++) {
      if (temp[i] != null) {
        LinkedList<NodeValue<KeyType, List<ValueType>>> list = temp[i];
        for (int j = 0; j < list.size(); j++) {
          put(list.get(j).getKey(), list.get(j).getValue());
        }
      }
    }
  }
  
  /**
   * this is a helper method to add the value into the list at a specified index in the 
   * hashtable and sort the contents of that list in ascending order after it has been added
   * @param Listvalue The List of values which the value will be added to 
   * @param value the value being added and sorted into the list of values.
   */
  private void appendList(List<ValueType> Listvalue, ValueType value) {
    Listvalue.add(value);
    Collections.sort(Listvalue);
  }

  @Override
  public int size() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void clear() {
    // TODO Auto-generated method stub
    
  }

}

