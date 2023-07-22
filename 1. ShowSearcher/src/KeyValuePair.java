// --== CS400 Project One File Header ==--
// Name: Utkarsh Sehgal
// CSL Username: sehgal
// Email: usehgal@wisc.edu
// Lecture #: 004
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
  public KeyType getk() {
    return this.k;
  }
  
  /**
   * @return the pair's stored ValueType
   */
  public ValueType getv() {
    return this.v;
  }
}
