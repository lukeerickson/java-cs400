// --== CS400 Red-Black Tree File Header ==--
// Name: <Luke Erickson>
// CSL Username: <lerickson>
// Email: <lerickson7@wisc.edu>
// Lecture #: <004 @4:00pm>
// Notes to Grader: <any optional extra notes to your grader>

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Stack;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Red-Black Tree implementation with a Node inner class for representing the nodes of the tree.
 * Currently, this implements a Binary Search Tree that we will turn into a red black tree by
 * modifying the insert functionality. In this activity, we will start with implementing rotations
 * for the binary search tree insert algorithm. You can use this class' insert method to build a
 * regular binary search tree, and its toString method to display a level-order traversal of the
 * tree.
 */
public class RedBlackTree<T extends Comparable<T>> implements SortedCollectionInterface<T> {

  /**
   * This class represents a node holding a single value within a binary tree the parent, left, and
   * right child references are always maintained.
   */
  protected static class Node<T> {
    public T data;
    public Node<T> parent; // null for root node
    public Node<T> leftChild;
    public Node<T> rightChild;
    public int blackHeight;

    public Node(T data) {
      this.data = data;
      // new nodes are red by default
      blackHeight = 0;
    }

    /**
     * @return true when this node has a parent and is the left child of that parent, otherwise
     *         return false
     */
    public boolean isLeftChild() {
      return parent != null && parent.leftChild == this;
    }

  }

  protected Node<T> root; // reference to root node of tree, null when empty
  protected int size = 0; // the number of values in the tree

  /**
   * Performs a naive insertion into a binary search tree: adding the input data value to a new node
   * in a leaf position within the tree. After this insertion, no attempt is made to restructure or
   * balance the tree. This tree will not hold null references, nor duplicate data values.
   * 
   * @param data to be added into this binary search tree
   * @return true if the value was inserted, false if not
   * @throws NullPointerException     when the provided data argument is null
   * @throws IllegalArgumentException when the newNode and subtree contain equal data references
   */
  @Override
  public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
    // null references cannot be stored within this tree
    if (data == null)
      throw new NullPointerException("This RedBlackTree cannot store null references.");

    Node<T> newNode = new Node<>(data);
    if (root == null) {
      root = newNode;
      size++;
      return true;
    } // add first node to an empty tree
    else {
      boolean returnValue = insertHelper(newNode, root); // recursively insert into subtree
      root.blackHeight = 1;
      if (returnValue)
        size++;
      else
        throw new IllegalArgumentException("This RedBlackTree already contains that value.");
      return returnValue;
    }
  }

  /**
   * Recursive helper method to find the subtree with a null reference in the position that the
   * newNode should be inserted, and then extend this tree by the newNode in that position.
   * 
   * @param newNode is the new node that is being added to this tree
   * @param subtree is the reference to a node within this tree which the newNode should be inserted
   *                as a descenedent beneath
   * @return true is the value was inserted in subtree, false if not
   */
  private boolean insertHelper(Node<T> newNode, Node<T> subtree) {
    int compare = newNode.data.compareTo(subtree.data);
    // do not allow duplicate values to be stored within this tree
    if (compare == 0)
      return false;

    // store newNode within left subtree of subtree
    else if (compare < 0) {
      if (subtree.leftChild == null) { // left subtree empty, add here
        subtree.leftChild = newNode;
        newNode.parent = subtree;
        // ensure that tree properties are maintained
        enforceRBTreePropertiesAfterInsert(newNode);
        return true;
        // otherwise continue recursive search for location to insert
      } else
        return insertHelper(newNode, subtree.leftChild);
    }

    // store newNode within the right subtree of subtree
    else {
      if (subtree.rightChild == null) { // right subtree empty, add here
        subtree.rightChild = newNode;
        newNode.parent = subtree;
        // ensure that tree properties are maintained
        enforceRBTreePropertiesAfterInsert(newNode);
        return true;
        // otherwise continue recursive search for location to insert
      } else
        return insertHelper(newNode, subtree.rightChild);
    }
  }

  /**
   * Performs the rotation operation on the provided nodes within this tree. When the provided child
   * is a leftChild of the provided parent, this method will perform a right rotation. When the
   * provided child is a rightChild of the provided parent, this method will perform a left
   * rotation. When the provided nodes are not related in one of these ways, this method will throw
   * an IllegalArgumentException.
   * 
   * @param child  is the node being rotated from child to parent position (between these two node
   *               arguments)
   * @param parent is the node being rotated from parent to child position (between these two node
   *               arguments)
   * @throws IllegalArgumentException when the provided child and parent node references are not
   *                                  initially (pre-rotation) related that way
   */
  private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
    // right rotation
    if (child.isLeftChild() && child.parent.equals(parent)) {
      Boolean replaceRoot = false;
      if (parent == root)
        replaceRoot = true;
      // save data from old tree
      Node oldParent = parent;
      Node leftChildRightSubtree = child.rightChild;
      if (parent.leftChild != null) {
        // rotate right child up
        parent = parent.leftChild;
        parent.parent = oldParent.parent;
      }

      // make the new parent's right child the old parent
      parent.rightChild = oldParent;
      //oldParent.parent = parent;

      // if the parent doesn't have a parent, make it the root node
      if (replaceRoot)
        root = parent;
      // link new parent to rest of tree
      else if (oldParent.parent.rightChild == oldParent)
        oldParent.parent.rightChild = parent;
      else if (oldParent.parent.leftChild == oldParent)
        oldParent.parent.leftChild = parent;
      // this child no longer has a reference, add it where it fits in the tree
      parent.rightChild.leftChild = leftChildRightSubtree;

      return;
    }

    // left rotation
    if (child.parent.equals(parent)) {
      Boolean replaceRoot = false;
      if (parent == root)
        replaceRoot = true;
      // save data from old tree
      Node oldParent = parent;
      Node rightChildLeftSubtree = child.leftChild;
      if (parent.rightChild != null) {
        // rotate right child up
        parent = parent.rightChild;
        parent.parent = oldParent.parent;
      }

      // make the new parent's left child the old parent
      parent.leftChild = oldParent;
      //oldParent.parent = parent;

      // if the parent doesn't have a parent, make it the root node
      if (replaceRoot)
        root = parent;
      // link new parent to rest of tree
      else if (oldParent.parent.rightChild == oldParent)
        oldParent.parent.rightChild = parent;
      else if (oldParent.parent.leftChild == oldParent)
        oldParent.parent.leftChild = parent;
      // this child no longer has a reference, add it where it fits in the tree
      parent.leftChild.rightChild = rightChildLeftSubtree;

      return;
    } else
      throw new IllegalArgumentException();
  }

  /**
   * Makes necessary changes to balance tree
   * 
   * @param node newly added red node
   */
  protected void enforceRBTreePropertiesAfterInsert(Node node) {
    // scenario 1 - parent is null or black --> do nothing
    if (node.parent == null || node.parent == root || node.parent.blackHeight == 1)
      return;

    // what do we do if grandparent is null?

    // scenario 2 - aunt is black
    else if (getAunt(node) == null || getAunt(node).blackHeight == 1) {
      // case 1 - node is left child of a left child
      if (node.isLeftChild() && node.parent.isLeftChild()) {
        node.parent.blackHeight = 1;
        node.parent.parent.blackHeight = 0;
        rotate(node.parent, node.parent.parent);
      }
      // case 2 - node is right child of a left child
      else if (!node.isLeftChild() && node.parent.isLeftChild()) {
        node.blackHeight = 1;
        node.parent.blackHeight = 0;
        node.parent.parent.blackHeight = 0;
        rotate(node, node.parent);
        rotate(node, node.parent);
        
      }
      // case 3 - node is left child of a right child
      else if (node.isLeftChild() && !node.parent.isLeftChild()) {
        node.blackHeight = 1;
        node.parent.blackHeight = 0;
        node.parent.parent.blackHeight = 0;
        rotate(node, node.parent);
        rotate(node, node.parent);
      }
      // case 4 - node is right child of a right child
      else if (!node.isLeftChild() && !node.parent.isLeftChild()) {
        node.parent.blackHeight = 1;
        node.parent.parent.blackHeight = 0;
        rotate(node.parent, node.parent.parent);
      }
    }

    // scenario 2 - aunt is red
    else if (getAunt(node).blackHeight == 0) {
      node.parent.blackHeight = 1;
      node.parent.parent.blackHeight = 0;
      getAunt(node).blackHeight = 1;

      enforceRBTreePropertiesAfterInsert(node.parent.parent);
    }

  }

  /**
   * helper method that returns a node's aunt
   * 
   * @param node we're starting with
   * @return given node's aunt
   */
  private Node getAunt(Node node) {
    if (node.parent == null || node.parent.parent == null || node.parent.parent.rightChild == null
        || node.parent.parent.leftChild == null)
      return null;
    else if (node.parent.isLeftChild())
      return node.parent.parent.rightChild;
    else
      return node.parent.parent.leftChild;
  }

  /**
   * Get the size of the tree (its number of nodes).
   * 
   * @return the number of nodes in the tree
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Method to check if the tree is empty (does not contain any node).
   * 
   * @return true of this.size() return 0, false if this.size() > 0
   */
  @Override
  public boolean isEmpty() {
    return this.size() == 0;
  }

  /**
   * Checks whether the tree contains the value *data*.
   * 
   * @param data the data value to test for
   * @return true if *data* is in the tree, false if it is not in the tree
   */
  @Override
  public boolean contains(T data) {
    // null references will not be stored within this tree
    if (data == null)
      throw new NullPointerException("This RedBlackTree cannot store null references.");
    return this.containsHelper(data, root);
  }

  /**
   * Recursive helper method that recurses through the tree and looks for the value *data*.
   * 
   * @param data    the data value to look for
   * @param subtree the subtree to search through
   * @return true of the value is in the subtree, false if not
   */
  private boolean containsHelper(T data, Node<T> subtree) {
    if (subtree == null) {
      // we are at a null child, value is not in tree
      return false;
    } else {
      int compare = data.compareTo(subtree.data);
      if (compare < 0) {
        // go left in the tree
        return containsHelper(data, subtree.leftChild);
      } else if (compare > 0) {
        // go right in the tree
        return containsHelper(data, subtree.rightChild);
      } else {
        // we found it :)
        return true;
      }
    }
  }

  /**
   * Returns an iterator over the values in in-order (sorted) order.
   * 
   * @return iterator object that traverses the tree in in-order sequence
   */
  @Override
  public Iterator<T> iterator() {
    // use an anonymous class here that implements the Iterator interface
    // we create a new on-off object of this class everytime the iterator
    // method is called
    return new Iterator<T>() {
      // a stack and current reference store the progress of the traversal
      // so that we can return one value at a time with the Iterator
      Stack<Node<T>> stack = null;
      Node<T> current = root;

      /**
       * The next method is called for each value in the traversal sequence. It returns one value at
       * a time.
       * 
       * @return next value in the sequence of the traversal
       * @throws NoSuchElementException if there is no more elements in the sequence
       */
      public T next() {
        // if stack == null, we need to initialize the stack and current element
        if (stack == null) {
          stack = new Stack<Node<T>>();
          current = root;
        }
        // go left as far as possible in the sub tree we are in un8til we hit a null
        // leaf (current is null), pushing all the nodes we fund on our way onto the
        // stack to process later
        while (current != null) {
          stack.push(current);
          current = current.leftChild;
        }
        // as long as the stack is not empty, we haven't finished the traversal yet;
        // take the next element from the stack and return it, then start to step down
        // its right subtree (set its right sub tree to current)
        if (!stack.isEmpty()) {
          Node<T> processedNode = stack.pop();
          current = processedNode.rightChild;
          return processedNode.data;
        } else {
          // if the stack is empty, we are done with our traversal
          throw new NoSuchElementException("There are no more elements in the tree");
        }

      }

      /**
       * Returns a boolean that indicates if the iterator has more elements (true), or if the
       * traversal has finished (false)
       * 
       * @return boolean indicating whether there are more elements / steps for the traversal
       */
      public boolean hasNext() {
        // return true if we either still have a current reference, or the stack
        // is not empty yet
        return !(current == null && (stack == null || stack.isEmpty()));
      }

    };
  }

  /**
   * This method performs an inorder traversal of the tree. The string representations of each data
   * value within this tree are assembled into a comma separated string within brackets (similar to
   * many implementations of java.util.Collection, like java.util.ArrayList, LinkedList, etc). Note
   * that this RedBlackTree class implementation of toString generates an inorder traversal. The
   * toString of the Node class class above produces a level order traversal of the nodes / values
   * of the tree.
   * 
   * @return string containing the ordered values of this tree (in-order traversal)
   */
  public String toInOrderString() {
    // use the inorder Iterator that we get by calling the iterator method above
    // to generate a string of all values of the tree in (ordered) in-order
    // traversal sequence
    Iterator<T> treeNodeIterator = this.iterator();
    StringBuffer sb = new StringBuffer();
    sb.append("[ ");
    if (treeNodeIterator.hasNext())
      sb.append(treeNodeIterator.next());
    while (treeNodeIterator.hasNext()) {
      T data = treeNodeIterator.next();
      sb.append(", ");
      sb.append(data.toString());
    }
    sb.append(" ]");
    return sb.toString();
  }

  /**
   * This method performs a level order traversal of the tree rooted at the current node. The string
   * representations of each data value within this tree are assembled into a comma separated string
   * within brackets (similar to many implementations of java.util.Collection). Note that the Node's
   * implementation of toString generates a level order traversal. The toString of the RedBlackTree
   * class below produces an inorder traversal of the nodes / values of the tree. This method will
   * be helpful as a helper for the debugging and testing of your rotation implementation.
   * 
   * @return string containing the values of this tree in level order
   */
  public String toLevelOrderString() {
    String output = "[ ";
    LinkedList<Node<T>> q = new LinkedList<>();
    q.add(this.root);
    while (!q.isEmpty()) {
      Node<T> next = q.removeFirst();
      if (next.leftChild != null)
        q.add(next.leftChild);
      if (next.rightChild != null)
        q.add(next.rightChild);
      output += next.data.toString();
      if (!q.isEmpty())
        output += ", ";
    }
    return output + " ]";
  }

  @Override
  public String toString() {
    return "level order: " + this.toLevelOrderString() + "/nin order: " + this.toInOrderString();
  }

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

  /**
   * Main method to run tests. Comment out the lines for each test to run them.
   * 
   * @param args
   */
  //public static void main(String[] args) {
    // System.out.println("Test 1 passed: " + test1());
    // System.out.println("Test 2 passed: " + test2());
    // System.out.println("Test 3 passed: " + test3());
    // System.out.println("Test 4 passed: " + test4());
    // System.out.println("Test 5 passed: " + test5());
  //}

}
