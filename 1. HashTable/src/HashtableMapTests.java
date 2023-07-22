// --== CS400 Project One File Header ==--
// Name: <Luke Erickson>
// CSL Username: <lerickson>
// Email: <lerickson7@wisc.edu>
// Lecture #: <004 @4:00pm>
// Notes to Grader: <any optional extra notes to your grader>

import java.util.NoSuchElementException;

public class HashtableMapTests<KeyType, ValueType> {

  /**
   * @return true if HashtableMap's constructors function as expected
   */
  public static boolean test1() {

    try {
      // scenario 1 - test constructor w/ parameter
      HashtableMap h1 = new HashtableMap(10);
      if (h1.size() != 0)
        return false;
      if (h1.getCapacity() != 10)
        return false;

      // scenario 2 - test constructor w/o parameter
      HashtableMap h2 = new HashtableMap();
      if (h2.size() != 0)
        return false;
      if (h2.getCapacity() != 20)
        return false;

    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * @return true if HashtableMap's put() and get() methods work correctly
   */
  public static boolean test2() {
    try {

      HashtableMap h1 = new HashtableMap(10);

      // ensure that the KeyValuePair (10,10) is successfully put into the array
      if (!h1.put(10, 10))
        return false;
      if ((int) h1.get(10) != 10)
        return false;

      if (h1.size() != 1)
        return false;

      // ensure that putting a null key returns false
      if (h1.put(null, null))
        return false;

      // ensure that adding a key that has already been used returns false
      if (h1.put(10, 10))
        return false;

      // add another key that results in the same index as a previous key, which should invoke
      // chaining
      KeyValuePair k2 = new KeyValuePair(0, 60);

      if (!h1.put(0, 60))
        return false;
      if ((int) h1.get(0) != 60)
        return false;
      if (h1.array[0].size() != 2)
        return false;
      if (h1.size() != 2)
        return false;

      // ensure that get throws a NoSuchElementException if you try searching for a key
      // that isn't contained w/in the hashtable
      try {
        h1.get(12);
      } catch (NoSuchElementException e) {
        // exception expected
      }


    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * @return true if the remove() method functions as expected
   */
  public static boolean test3() {
    try {

      HashtableMap h1 = new HashtableMap(10);
      KeyValuePair k1 = new KeyValuePair(10, 10);

      // scenario 1 - remove one KeyValuePair
      h1.put(k1.getK(), k1.getV());
      if (!h1.remove(k1.getK()).equals(k1.getV()))
        return false;

      if (h1.size() != 0)
        return false;

      KeyValuePair k2 = new KeyValuePair(0, 60);

      // scenario 2 - remove two KeyValuePairs in the same chain
      // they should be removed in the correct order
      h1.put(k1.getK(), k1.getV());
      h1.put(k2.getK(), k2.getV());

      if (!h1.remove(k1.getK()).equals(k1.getV()))
        return false;
      if (h1.size() != 1)
        return false;

      if (!h1.remove(k2.getK()).equals(k2.getV()))
        return false;
      if (h1.size() != 0)
        return false;


    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * @return true if the load factor is correctly resized and all keys are correctly rehashed when
   *         the load factor >= .75
   */
  public static boolean test4() {
    try {

      HashtableMap h1 = new HashtableMap(5);

      h1.put(1, 10);
      h1.put(6, 10);
      h1.put(17, 10);
      h1.put(18, 10);

      // at this point, the load factor is >= .75,
      // and the table should be resized and rehashed
      if (h1.getCapacity() != 10)
        return false;

    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * @return true if the clear() methods works as expected
   */
  public static boolean test5() {
    try {
      HashtableMap h1 = new HashtableMap(5);

      h1.put(10, 10);
      h1.put(15, 10);
      h1.put(17, 10);
      h1.put(18, 10);
      
      h1.clear();
      
      // the size of the array should go back to 0
      if(h1.size() != 0)
        return false;
      
      // each index should be null
      // we'll check the key of 10, which was previously stored in the array before clearing it
      try {
        h1.get(10);
      } catch (NoSuchElementException e) {
        // exception expected
      }
      
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * @return true if all tests perform as expected
   */
  public static boolean testAll() {
    return test1() && test2() && test3() && test4() && test5();
  }

  /**
   * @param args prints a message to the console as to whether all tests perform as expected
   */
  public static void main(String[] args) {
    System.out.println(testAll());

  }

}
