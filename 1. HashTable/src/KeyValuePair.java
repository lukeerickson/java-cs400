// --== CS400 Project One File Header ==--
// Name: <Luke Erickson>
// CSL Username: <lerickson>
// Email: <lerickson7@wisc.edu>
// Lecture #: <004 @4:00pm>
// Notes to Grader: <any optional extra notes to your grader>


/**
 * Stores a KeyType and a ValueType, linking them together
 * 
 * @author lukee
 *
 * @param <KeyType>
 * @param <ValueType>
 */
public class KeyValuePair<KeyType, ValueType> {
  
  private KeyType k;
  private ValueType v;
  
  /**
   * Basic constructor for KeyValuePair
   * 
   * @param k
   * @param v
   */
  public KeyValuePair(KeyType k, ValueType v) {
    this.k = k;
    this.v = v;
  }
  
  /**
   * @return the pair's stored KeyType
   */
  public KeyType getK() {
    return this.k;
  }
  
  /**
   * @return the pair's stored ValueType
   */
  public ValueType getV() {
    return this.v;
  }
}
