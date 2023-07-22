// --== CS400 Red-Black Tree Tester File Header ==--
// Name: <Luke Erickson>
// CSL Username: <lerickson>
// Email: <lerickson7@wisc.edu>
// Lecture #: <004 @4:00pm>
// Notes to Grader: <any optional extra notes to your grader>

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RedBlackTreeTest {

  /**
   * Test adding a node to a tree w/ a black parent
   */
  @Test
  void test1() {
    RedBlackTree tree = new RedBlackTree();
    tree.insert(40);
    tree.insert(2);

    assertTrue(tree.root.data.equals(40));
    assertTrue(tree.root.blackHeight == 1);
    assertTrue(tree.root.leftChild.data.equals(2));
    assertTrue(tree.root.leftChild.blackHeight == 0);

  }

  /**
   * Test adding a node to a tree w/ a red aunt
   */
  @Test
  void test2() {
    RedBlackTree tree = new RedBlackTree();
    tree.insert(23);
    tree.insert(7);
    tree.insert(41);
    tree.insert(37);

    // test to see if all nodes are in the correct positions 
    // and that they are the correct colors
    assertTrue(tree.root.data.equals(23));
    assertTrue(tree.root.blackHeight == 1);
    assertTrue(tree.root.leftChild.data.equals(7));
    assertTrue(tree.root.leftChild.blackHeight == 1);
    assertTrue(tree.root.rightChild.data.equals(41));
    assertTrue(tree.root.rightChild.blackHeight == 1);
    assertTrue(tree.root.rightChild.leftChild.data.equals(37));
    assertTrue(tree.root.rightChild.leftChild.blackHeight == 0);

  }
  
  /**
   * Test adding a node to a tree w/ a black aunt
   */
  @Test
  void test3() {
    // case 1 - node is left child of a left child
    RedBlackTree tree = new RedBlackTree();
    tree.insert(10);
    tree.insert(8);
    tree.insert(12);
    tree.insert(6);
    tree.insert(5);
    
    //System.out.println(tree.toLevelOrderString());

    // test to see if all nodes are in the correct positions 
    // and that they are the correct colors
    assertTrue(tree.root.data.equals(10));
    assertTrue(tree.root.blackHeight == 1);
    assertTrue(tree.root.leftChild.data.equals(6));
    assertTrue(tree.root.leftChild.blackHeight == 1);
    assertTrue(tree.root.rightChild.data.equals(12));
    assertTrue(tree.root.rightChild.blackHeight == 1);
    assertTrue(tree.root.leftChild.leftChild.data.equals(5));
    assertTrue(tree.root.leftChild.leftChild.blackHeight == 0);
    assertTrue(tree.root.leftChild.rightChild.data.equals(8));
    assertTrue(tree.root.leftChild.rightChild.blackHeight == 0);
    
    // case 2 - node is right child of a left child
    RedBlackTree tree2 = new RedBlackTree();
    tree2.insert(10);
    tree2.insert(8);
    tree2.insert(12);
    tree2.insert(5);
    tree2.insert(6);

    // test to see if all nodes are in the correct positions 
    // and that they are the correct colors
    assertTrue(tree2.root.data.equals(10));
    assertTrue(tree2.root.blackHeight == 1);
    assertTrue(tree2.root.leftChild.data.equals(6));
    assertTrue(tree2.root.leftChild.blackHeight == 1);
    assertTrue(tree2.root.rightChild.data.equals(12));
    assertTrue(tree2.root.rightChild.blackHeight == 1);
    assertTrue(tree2.root.leftChild.leftChild.data.equals(5));
    assertTrue(tree2.root.leftChild.leftChild.blackHeight == 0);
    assertTrue(tree2.root.leftChild.rightChild.data.equals(8));
    assertTrue(tree2.root.leftChild.rightChild.blackHeight == 0);
    
  }

}
