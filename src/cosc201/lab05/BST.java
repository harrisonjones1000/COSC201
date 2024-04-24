package cosc201.lab05;

import java.util.ArrayList;
import java.util.ArrayDeque;

/**
 * A basic binary search tree for extending in lab 5.
 * 
 * @author Michael Albert
 */
public class BST {

  private static final String PADDING = " ".repeat(4); // Used in toString
  private static final boolean RIGHT = false;
  private static final boolean LEFT = true;
  private Node root = null;

  public BST() {};

  public boolean isEmpty() {
    return root == null;
  }

  /**
   * Determines whether a string is stored in this tree.
   * @param s The string
   * @return true, if s occurs in the tree.
   */
  public boolean contains(String s) {
    return findNode(s, root) != null;
  }

  /**
   * Adds (if not already present) a string to this tree.
   * @param s the string to add
   * @return true, if s was added, false if s was already present
   */
  public boolean add(String s) {
    if (isEmpty()) {
      root = new Node(s);
      root.height=0;
      return true;
    }
    Node parent = null, child = this.root;
    while (child != null) {
      if (child.key.equals(s)) return false;
      parent = child;

      if (parent.key.compareTo(s) < 0) {
        child = parent.right;
      } else {
        child = parent.left;
      }
    }
    addLink(parent, new Node(s));
    int height = 1;
    if(parent.height<height) parent.height = height;
    parent.size++;
    while(parent.parent!=null){
      parent = parent.parent;
      height++;
      if(parent.height<height) parent.height = height;
      parent.size++;
    }
    return true;
  }

  /**
   * Delete a string from the BST if it's present.
   * @param s the string to be deleted
   * @return true if s was deleted, false if s was not in the tree
   */
  public boolean delete(String s) {
    Node n = findNode(s, root);
    if (n == null) return false;
    delete(n);
    return true;
  }
  
  /**
   * Preorder traverse the BST. This is for illustrative purposes only,
   * traversal is generally an idea used in an algorithm, not a method
   * per se.
   *
   * @return the strings stored in this tree in preorder
   */
   
  public ArrayList<String> preorder() {
    ArrayList<String> result = new ArrayList<>();
    preorder(root, result);
    return result;
  }
   
  // Helper method for preorder traversal
  // Use r as working storage to preorder traverse the tree below n
  private void preorder(Node n, ArrayList<String> r) {
    if (n == null) return;
    r.add(n.key);
    preorder(n.left, r);
    preorder(n.right, r);
  }
  
  /**
   * Inorder traverse the BST. This is for illustrative purposes only,
   * traversal is generally an idea used in an algorithm, not a method
   * per se.
   *
   * @return the strings stored in this tree in order
   */
   
  public ArrayList<String> inorder() {
    ArrayList<String> result = new ArrayList<>();
    inorder(root, result);
    return result;
  }
   
  // Helper method for inorder traversal
  // Use r as working storage to inorder traverse the tree below n
  private void inorder(Node n, ArrayList<String> r) {
    if (n == null) return;
    inorder(n.left, r);
    r.add(n.key);
    inorder(n.right, r);
  } 
  
  /**
   * Postorder traverse the BST. This is for illustrative purposes only,
   * traversal is generally an idea used in an algorithm, not a method
   * per se.
   *
   * @return the strings stored in this tree in postorder
   */
   
  public ArrayList<String> postorder() {
    ArrayList<String> result = new ArrayList<>();
    postorder(root, result);
    return result;
  }
   
  // Helper method for postorder traversal
  // Use r as working storage to postorder traverse the tree below n
  private void postorder(Node n, ArrayList<String> r) {
    if (n == null) return;
    postorder(n.left, r);
    postorder(n.right, r);
    r.add(n.key);
  }
  
  /**
   * Traverse the BST in level order, i.e., first the root, then all its 
   * children, then all its grandchildren and so on. This is for illustrative
   * purposes only, traversal is generally an idea used in an algorithm, not
   * a method per se.
   *
   * @return the strings stored in this tree in level order
   */
  public ArrayList<String> levelorder() {
    ArrayList<String> result = new ArrayList<>();
    if (isEmpty()) return result;
    ArrayDeque<Node> q = new ArrayDeque<>();
    q.add(root);
    while (!q.isEmpty()) {
      Node n = q.remove();
      result.add(n.key);
      if (n.left  != null) q.add(n.left);
      if (n.right != null) q.add(n.right);
    }
    return result;
  }

  /**
   * Returns a string representation of the tree. The tree is printed with
   * the root at the left, and left children lie below a node, right children
   * above it. So the tree is read from the root down, left to right.
   */
  public String toString() {
    if (isEmpty()) return "<>";
    StringBuilder result = new StringBuilder();
    addString(root, result, "");
    return result.toString();
  }
  
  // Finds the node (if any) containing s below n - returns null if there is
  // no such node
  private Node findNode(String s, Node n) {
    if (n == null || n.key.equals(s)) return n;
    if (n.key.compareTo(s) < 0) {
      return findNode(s, n.right);
    } else {
      return findNode(s, n.left);
    }
  }

  // Delete a node
  // Assumption: n is not null
  private void delete(Node n) {
    //is root
    if (n == root) {
      deleteRoot(); return;
    }
    Node parent = n.parent;
    int height;

    
    if(n.left==null&&n.right==null){ //n has no children
      if(parent.right.key.equals(n.key)) parent.right=null;
      parent.left=null;
      n.parent=null;

      if(parent.left==null&&parent.right==null){//if parent has no children, parent height is 0
        parent.height = 0;
      }
      height = parent.height; //parent other child not null, parent height as supposed to be
      parent.size--;

      while(parent.parent!=null){
        parent=parent.parent;
        parent.size--;
        height++; //the height the next parent should be
        
        if(parent.right==null) parent.height = parent.left.height+1;
        else if(parent.left==null) parent.height = parent.right.height+1;
        else if(parent.right.height<height&&parent.left.height<height){
          parent.height = height;
        }//if left or right greater/equal to supposed height, parent is correct height
      }
      return;
    }

    if((n.left == null&&n.right != null)||(n.left != null&&n.right == null)){ //one child
      if(n.left == null){

        addLink(n.parent, n.right, linkType(n.parent, n)); //connects child to parent.right
        //if left side smaller than, null, or same height as right, parent decrease by 1, otherwise parent correct height
        if(parent.left==null) parent.height--;
        else if(parent.left.height<=parent.right.height) parent.height--;
      }else{
        addLink(n.parent, n.left, linkType(n.parent, n));
        if(parent.right==null) parent.height--;
        else if(parent.right.height<=parent.left.height) parent.height--;
      }
      
      height = parent.height;
      parent.size--;

      while(parent.parent!=null){
        parent=parent.parent;
        parent.size--;
        height++;
        
        if(parent.right==null) parent.height = parent.left.height+1;
        else if(parent.left==null) parent.height = parent.right.height+1;
        else if(parent.right.height<height&&parent.left.height<height){
          parent.height = height;
        }
      }
      return;
    }
    //2 children
    Node sn = successor(n); //finds right subtrees minimum value, sn
    String s = sn.key;
    delete(sn); //deletes it
    n.key = s; //sets node
  }

  // Delete the root. This is a special case because the root's
  // parent doesn't exist.
  private void deleteRoot() {
    if (root.left == null) {
      root = root.right;
      root.parent = null;
      return;
    }

    if (root.right == null) {
      root = root.left;
      root.parent = null;
      return;
    }

    Node n = successor(root);
    String s = n.key;
    delete(n);
    root.key = s;
  }
  
  // Adds the string contained in a node together with a suitable padding
  // prefix to a StringBuilder -- used in toString()
  private void addString(Node n, StringBuilder result, String prefix) {
    if (n == null) return;
    addString(n.right, result, prefix + PADDING);
    result.append(prefix + n.key);
    result.append("\n");
    addString(n.left, result, prefix + PADDING);
  }

  // Finds the successor of a node, used in the 'difficult' delete case.
  // Assumption: n has a non-null right child.
  // Finds the minimum value in the nodes right subtree.
  private Node successor(Node n) {
    Node result = n.right;
    while (result.left != null) {
      result = result.left;
    }
    return result;
  }

  // Determines the type of a parent-child link
  // Assumption: this is a parent-child pair and neither is null
  private boolean linkType(Node parent, Node child) {
    if (parent.key.compareTo(child.key) < 0) {
      return RIGHT;
    } else {
      return LEFT;
    }
  }

  // Links a parent and child node
  // Assumption: neither node is null or has a null key
  //             the two keys are different
  private void addLink(Node parent, Node child) {
    child.parent = parent;
    if (child.key.compareTo(parent.key)< 0) {
      parent.left = child;
    } else {
      parent.right = child;
    }
  }

  // Links a parent and child node in the indicated direction
  // Assumption: parent is not null (child might be)
  private void addLink(Node parent, Node child, boolean type) {
    if (type == RIGHT) {
      parent.right = child;
      
    } else {
      parent.left = child;
    }
    if (child != null){
      System.out.println("haha");
      child.parent = parent;
    }
  }

  private ArrayList<String> rectangleBelow(Node n) {
    ArrayList<String> result = new ArrayList<>();
    if (n == null) return result;
    ArrayList<String> left = rectangleBelow(n.left);
    ArrayList<String> right = rectangleBelow(n.right);
    int leftWidth = 0;
    int rightWidth = 0;
    if (!left.isEmpty()) {
      leftWidth = left.get(0).length();
    }
    if (!right.isEmpty()) {
      rightWidth = right.get(0).length();
    }
    int keyWidth = n.key.length();
    // Make a string of leftWidth spaces
    String leftPadding = " ".repeat(leftWidth);
    String rightPadding = " ".repeat(rightWidth);
    String midPadding = " ".repeat(keyWidth);
    result.add(leftPadding + n.key + rightPadding);
    for(int i = 0; i < Math.max(left.size(), right.size()); i++) {
      String thisLine = "";
      if (i < left.size()) {
        thisLine += left.get(i);
      } else {
        thisLine += leftPadding;
      }
      thisLine += midPadding;
      if (i < right.size()) {
        thisLine += right.get(i);
      } else {
        thisLine += rightPadding;
      }
      result.add(thisLine);
    }
    return result;
  }

  /*
   * An alternative way to print the tree. This method uses a different
   * approach to the problem of printing the tree. It prints the tree
   * with the root at the top.
   * 
   */
    public String altToString() {
      ArrayList<String> lines = rectangleBelow(root);
      StringBuilder result = new StringBuilder();
      for (String line : lines) {
        result.append(line);
        result.append("\n");
      }
      // Delete the final newline character
      if (result.length() > 0) result.deleteCharAt(result.length() - 1);
      return result.toString();
    }

    public int size(String s){
      Node n = findNode(s, root);
      if (n==null) return 0;
      return n.size;
    }

    public int height(String s){
      Node n = findNode(s, root);
      if (n==null) return -1;
      return n.height;
    }

    public static BST makeBalanced(String[] dictionary){
      BST t = new BST();
      return t;
    }

  private class Node {
    Node parent = null;
    Node left = null;
    Node right = null;
    String key;
    int size;
    int height;
   
    Node(String key) {
      this.key = key;
      this.size=1;
    }
 
  }
  
}
