
public class NodeValue<KeyValue, ValueType> {
  private KeyValue key;
  private ValueType value;
  
  public NodeValue(KeyValue key, ValueType value) {
    this.key = key;
    this.value = value;
  }
  
  public KeyValue getKey(){
    return key;
  }
  
  public ValueType getValue() {
    return value;
  }

}
