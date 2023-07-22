import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AlgorithmEngineerTests {
/**
 * A basic test for adding new nodes and checking order
 */
  @Test
  public void test1() {
    RedBlackTree<Integer> tree = new RedBlackTree<>();
    tree.insert(5);
    tree.insert(10);
    tree.insert(30);
    tree.insert(15);
    tree.insert(35);
    tree.insert(85);
    tree.insert(20);
    tree.insert(25);
    Assertions.assertEquals("[ 5, 10, 15, 20, 25, 30, 35, 85 ]", tree.toInOrderString(),
        "Insert Order Test Failed: your nodes are not in the correct order");
  }
/**
* A basic test for adding new nodes and checking color/black height
*/
    @Test
    public void test2() {
      RedBlackTree<Integer> tree = new RedBlackTree<>();
      tree.insert(5);
      tree.insert(10);
      tree.insert(30);
      tree.insert(15);
      Assertions.assertEquals(1, tree.root.blackHeight, "Color Test Failed: wrong color root");
      Assertions.assertEquals(1, tree.root.leftChild.blackHeight,
          "Color Test Failed: 10 is the wrong color");
      Assertions.assertEquals(1, tree.root.rightChild.blackHeight,
          "Color Test Failed: 30 is the wrong color");
      Assertions.assertEquals(0, tree.root.rightChild.leftChild.blackHeight,
          "Color Test Failed: 15 is the wrong color");
    }
  /**
   * A test to test case 3, where the new red node has a red parent and a red uncle. Extension of
   * last weeks tests
   */
  @Test
  public void testCase3() {
    RedBlackTree<Integer> tree = new RedBlackTree<>();
    tree.insert(2);
    tree.insert(6);
    tree.insert(69);
    tree.insert(9);
    Assertions.assertEquals(1, tree.root.blackHeight, "Case 3 Test Failed: wrong color root");
    Assertions.assertEquals(1, tree.root.leftChild.blackHeight,
        "Case 3 Test Failed: 6 is the wrong color");
    Assertions.assertEquals(1, tree.root.rightChild.blackHeight,
        "Case 3 Test Failed: 69 is the wrong color");
    Assertions.assertEquals(0, tree.root.leftChild.leftChild.blackHeight,
        "Case 3 Test Failed: 2 is the wrong color");
  }

  /**
   * A test to test case 2, where the new red node is a a child of the same side of the parent's
   * side
   * 
   */
  @Test
  public void testCase2() {
    RedBlackTree<Integer> tree = new RedBlackTree<>();
    tree.insert(69);
    tree.insert(8);
    tree.insert(72);
    tree.root.rightChild.blackHeight = 1;
    tree.insert(2);
    Assertions.assertEquals("[ 2, 8, 69, 72 ]", tree.toInOrderString(),
        "Case 2 Test Failed: your nodes are not in the correct order");
    Assertions.assertEquals(1, tree.root.blackHeight, "Case 2 Test Failed: wrong color root");
    Assertions.assertEquals(0, tree.root.leftChild.blackHeight,
        "Case 1 Test Failed: 2 is the wrong color");
    Assertions.assertEquals(0, tree.root.rightChild.blackHeight,
        "Case 1 Test Failed: 69 is the wrong color");
    Assertions.assertEquals(1, tree.root.rightChild.rightChild.blackHeight,
        "Case 1 Test Failed: 72 is the wrong color");
  }
  /**
   * A test to test case 2, where the new red node is a a child of the opposite side of the parent's
   * side
   * 
   */
  @Test
  public void testCase1() {
    RedBlackTree<Integer> tree = new RedBlackTree<>();
    tree.insert(69);
    tree.insert(8);
    tree.insert(72);
    tree.root.rightChild.blackHeight = 1;
    tree.insert(22);
    Assertions.assertEquals("[ 8, 22, 69, 72 ]", tree.toInOrderString(),
        "Case 2 Test Failed: your nodes are not in the correct order");
    Assertions.assertEquals(1, tree.root.blackHeight, "Case 2 Test Failed: wrong color root");
    Assertions.assertEquals(0, tree.root.leftChild.blackHeight,
        "Case 1 Test Failed: 8 is the wrong color");
    Assertions.assertEquals(0, tree.root.rightChild.blackHeight,
        "Case 1 Test Failed: 69 is the wrong color");
    Assertions.assertEquals(1, tree.root.rightChild.rightChild.blackHeight,
        "Case 1 Test Failed: 72 is the wrong color");
  }
}
