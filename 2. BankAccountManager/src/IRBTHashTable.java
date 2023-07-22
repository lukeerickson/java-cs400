import java.util.NoSuchElementException;

/**
 * Interface for the main hashtable which implements Red-Black Trees when handling collisions
 */
public interface IRBTHashTable<KeyType, ValueType> extends MapADT<KeyType, ValueType> {
    /*
     * HashTable uses individual RBT implementation to handle chaining rather than previous linked list.
     * Implement all methods within the MapADT to construct a functioning HashTable that maps a key Account Number (int)
     * to a value Account object. The HashTable CANNOT contain duplicate Keys -> put() method should return false when
     * adding a key-value pair with an existing key.
     */
}
