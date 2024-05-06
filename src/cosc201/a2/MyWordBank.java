package cosc201.a2;

import java.util.*;

public class MyWordBank implements WordBank{
  public HashMap<String, Node> nodes = new HashMap<>();
  private Node root = null;
  private static final long NOT_FOUND = -1;

  public MyWordBank(){}

    /**
   * Returns the value associated with the word, or NOT_FOUND if the word is not
   * in the bank.
   * 
   * @param word A word whose value is to be determined
   * @return The word's value or NOT_FOUND
   */
  public long getValue(String word){
    if(nodes.get(word)==null) return NOT_FOUND;
    return nodes.get(word).getValue();
  }

  /**
   * Add a word to the bank with the given value. If the word is not valid, or
   * the value is negative throw an IllegalArgumentException.
   * 
   * Multiple of the same word can be added. If a word already has a value, adding 
   * another instance of the word with a new value will override the previous value.
   * 
   * @param word A word to be added to the bank
   * @param value Its value
   */
  public void addWord(String word, long value){
    if((isValidWord(word)&&value>=0)){
      Node n = nodes.get(word);
      if(n!=null){ //if word already exists
        if(n.getValue()==value){
          n.count++;
        }else{ //if word doesnt share same value, remove old Node from BST and map, re add
          Node w = new Node(value, word, n.count++);
          removeWord(n.word);
          nodes.put(word,w);
          add(w); 
        }
      }else{ //word doesnt already exist
        n = new Node(value, word);
        nodes.put(word, n);
        add(n);
      }
    }else{
      throw new IllegalArgumentException();
    }
  }

  /**
   * Adds a Node to the BST
   * @param n the Node to add
   */
  public void add(Node n){
    if(root==null){
      root = n;
    }else{
      Node parent = root;
      Node child = root;
      while(child!=null){
        parent=child;
        if (Long.compare(n.getValue(), child.getValue())>0){ //if input greater than node, go right
          child = parent.right;
        }else{ //if input less than or equal to node, move left 
          child = parent.left; 
        }
      }
      addLink(parent, n);
    }
  }
  
  public void addLink(Node parent, Node n){
    if(Long.compare(n.getValue(), parent.getValue())>0){
      parent.right=n;
    }else{
      parent.left=n;
    }
    n.parent = parent;
  }

  /**
   * Add a collection of words to the bank with the given value. Ignore any
   * invalid words but if the value is negative throw an 
   * IllegalArgumentException.
   * 
   * @param words A collection of words to be added to the bank
   * @param value Their common value
   */
  public void addWords(Collection<String> words, long value){
    if(value>=0){
      Iterator<String> iterator = words.iterator();
        while(iterator.hasNext()){
          String word = iterator.next();
          if(isValidWord(word)){
            addWord(word, value);
          }
        }
    }else{
      throw new IllegalArgumentException();
    }
  }

  /**
   * Remove a word from the bank. If the word is invalid or not in
   * the bank, do nothing.
   * 
   * @param word A word to be removed from the bank
   */
  public void removeWord(String word){
    Node n = nodes.get(word);
    if(n==null) return;

    if(n == root){ //n is root
      if(root.left == null){
        root=root.right;
        root.parent=null;
      }else if (root.right == null){
        root = root.left;
        root.parent = null;
      }else{
        awesomeMethod(n);
      }
      nodes.put(word,null);
      return;
    }

    if(n.left==null&&n.right==null){ //n has no children
      if(n.parent.right!=null&&n.parent.right.word.equals(n.word)){
        n.parent.right=null;
        n.parent=null;
      }else{
        n.parent.left=null;
      }
      n.parent=null;

    }else if(n.left==null&&n.right != null){//one child on right
      if(n.parent.right!=null&&n.parent.right.word.equals(n.word)){
        n.parent.right=n.right;
      }else{
        n.parent.left=n.right;
      }
      n.right.parent=n.parent;

    }else if(n.left != null&&n.right == null){//one child on left
      if(n.parent.right!=null&&n.parent.right.word.equals(n.word)){
        n.parent.right=n.left;
      }else{
        n.parent.left=n.left;
      }
      n.left.parent=n.parent;
    }else{ //2 children
      awesomeMethod(n);
    }
    nodes.put(word,null);
  }

  /*
   * Find the successor of a node, aka the right subtrees leftmost node.
   */
  public Node successor(Node n) {
    Node result = n.right;
    while (result.left != null) {
      result = result.left;
    }
    return result;
  }

  /**
   * Replaces a node n, when node n has 2 children, with its successor
   * @param n the node needed to be removed
   */
  public void awesomeMethod(Node n){
    Node sn = successor(n); //finds right subtrees minimum value, sn
    if(n.right!=sn){
      sn.parent.left=sn.right;
      if(sn.right!=null) sn.right.parent=sn.parent;
      sn.right=n.right;
      n.right.parent=sn;
    }
    sn.left=n.left;
    n.left.parent=sn;
    
    if(n==root){
      root=sn;
      sn.parent=null;
    }else{
      sn.parent=n.parent;
      if(n.parent.right!=null) if(n.parent.right.word.equals(n.word)){
        n.parent.right=sn;
      }else{
        n.parent.left = sn;
      }
    }
  }

  /**
   * Remove a collection of words from the bank. Ignore any words that are not
   * in the bank.
   * 
   * @param words The collection of words to remove
   */
  public void removeWords(Collection<String> words){
    Iterator<String> iterator = words.iterator();
    while(iterator.hasNext()){
      String word = iterator.next();
      removeWord(word);
      
    }
  }

  /**
   * Return the words in the bank of the given value.
   * 
   * @param value The value to be searched for.
   * @return The words in the bank of that value.
   */
  public Set<String> getWords(long value){
    Set<String> s = new HashSet<String>();
    getWords(value, s, root);
    return s;
  }

  public void getWords(long value, Set<String> s, Node n){
    if(n==null) return;
    if(Long.compare(n.getValue(), value)>0) getWords(value, s, n.left);
    else if(Long.compare(n.getValue(), value)<0) getWords(value, s, n.right);
    else if(Long.compare(n.getValue(), value)==0){
      s.add(n.word);
      getWords(value, s, n.left);
      getWords(value, s, n.right);
    }
  }

  /**
   * Return the words in the bank with values in the range lowValue inclusive to
   * highValue exclusive.
   * 
   * @param lowValue The lowest value (inclusive)
   * @param highValue The highest value (exclusive)
   * @return The words in the bank with values in the range lowValue to highValue.
   */
  public Set<String> getWords(long lowValue, long highValue){
    Set<String> s = new HashSet<String>();
    getWords(lowValue, highValue, s, root);
    return s;
  }

  public void getWords(long lowValue, long highValue, Set<String> s, Node n){
    if(n==null) return;
    int a = Long.compare(n.getValue(), lowValue);
    int b = Long.compare(n.getValue(), highValue);

    if(a>=0&&b==-1){
      s.add(n.word);
      getWords(lowValue, highValue, s, n.left);
      getWords(lowValue, highValue, s, n.right);
    }if(a==-1){//if node less than lowValue, go right
      getWords(lowValue, highValue, s, n.right); 
    }else if(b>=0){ //if node greater than highValue, go left
      getWords(lowValue, highValue, s, n.left);
    }
  }

  /**
   * Return true if the word consists entirely of letters
   * in the range a-z. Note this is provided - you do not need to implement it.
   * 
   * @param word A word to be tested
   * @return true if the word is formed from a-z, false otherwise.
   */
  public static boolean isValidWord(String word) {
    return word.matches("[a-z]+");
  }

  // Writing tool operations.
  //Can have repetitions of words. 

  /**
   * Return the word in the bank from the given collection with the maximum
   * value. If there are multiple words with the same value, any one of them could be
   * returned.
   * 
   * Words not in the bank are to be ignored, and if no words from the collection
   * belong to the bank then the method should return null.
   * 
   * @param words A collection of words.
   * @return A word of maximum value among the words in the collection.
   */
  public String getMaximumWord(Collection<String> words){
    Iterator<String> iterator = words.iterator();
    String word = iterator.next();
    Long max = null;
    String curr = null;
    while(iterator.hasNext()){
      word = iterator.next();
      if(nodes.get(word)!=null){
        if(max==null||nodes.get(word).getValue()>max){
          max = nodes.get(word).getValue();
          curr = word;
        }
      }
    }
    return curr;
  }

  /**
   * Return the word in the bank from the given collection with the minimum
   * value. If there are multiple words with the same value, any one of 
   * them could be returned.
   * 
   * Words not in the bank, or invalid words, are to be ignored, and if no
   * words from the collection belong to the bank then the method should 
   * return null.
   * 
   * @param words A collection of words.
   * @return A word of minimum value among the words in the collection
   */
  public String getMinimumWord(Collection<String> words){
    Iterator<String> iterator = words.iterator();
    String word = iterator.next();
    Long min = null;
    String curr = null;
    while(iterator.hasNext()){
      word = iterator.next();
      if(nodes.get(word)!=null){
        if(min==null||nodes.get(word).getValue()<min){
          min = nodes.get(word).getValue();
          curr = word;
        }
      }
    }
    return curr;
  }

  /**
   * Return the word in the bank from the given collection with the median value.
   * If there are multiple words with the same value, any one of them could be
   * returned.
   * 
   * Words not in the bank or invalid words are to be ignored, and if no words 
   * from the collection belong to the bank then the method should return null.
   * 
   * In the case where the number of words in the collection that are in the bank
   * is even, and the median value is not well-defined (e.g., the values are 
   * 1, 2, 3, 4), the method should return a word with the higher value of the two 
   * that could be considered the median.
   * 
   * @param words A collection of words.
   * @return A word of median value among the words in the collection
   */
  public String getMedianWord(Collection<String> words){
    Pair[] p = getSortedPairs(words);
    if (p==null) return null;
    int l = p.length;
    if(l%2!=0) return p[(l-1)/2].word;
    return p[l/2].word;
  }

  /**
   * Return the word in the bank from the given collection with the mean value.
   * If there are multiple words with the same value, any one of them could be
   * returned.
   * 
   * Words not in the bank or invalid words are to be ignored, and if no words 
   * from the collection belong to the bank then the method should return null.
   * 
   * In the case where the mean value is not an integer, or there are no words
   * with the mean value in the list then one from the list whose value is as 
   * close as possible to the mean should be returned.
   * 
   * @param words A collection of words
   * @return A word of nearest to mean value among the words in the collection
   */
  public String getMeanWord(Collection<String> words){
    Pair[] p = getSortedPairs(words);
    if (p==null) return null;
    int l = p.length;
    long total = 0;
    for(int i=0; i<l; i++){
      total+=p[i].value;
    }
    double mean = (double)(total/l);
    int lowest = 0;
    for(int i=1; i<l; i++){
      if(Math.abs(p[lowest].value-mean)>Math.abs(p[i].value-mean)) lowest = i;
    }
    return p[lowest].word;
  }

  /**
   * Generates a pair array of valid words in the wordbank, from lowest to highest values
   * 
   * @param words A collection of words
   * @return a sorted pair array
   */
  public Pair[] getSortedPairs(Collection<String> words){
    int l = words.size();
    String[] array = words.toArray(new String[l]);
    Pair[] p = new Pair[l];
    int d = 0;
    for(int i = 0; i < l; i++){
      if(nodes.get(array[i])==null){
        d++;
      }else{
        p[i-d] = new Pair(array[i], nodes.get(array[i]).getValue());
      }
    }
    l=l-d;
    if (l==0) return null;
    p = Arrays.copyOfRange(p,0,l);
    Arrays.sort(p);
    return p;
  }

  /**
   * Return the value of a document, considered as a collection of words. The
   * value of a document is the sum of the values of all the distinct words 
   * it contains, meaning that invalid words or words not in the bank are 
   * ignored, as are the second and subsequent occurrences of a word.
   * 
   * @param words A collection of words
   * @return The value of the document
   */
  public long getDocumentValue(Collection<String> words){
    int l = words.size();
    long value = 0;
    String[] array = words.toArray(new String[l]);
    for(int i=0; i<l; i++){
      if(nodes.get(array[i])!=null) value += nodes.get(array[i]).getValue();
    }
    return value;
  }

  /**
   * For the given wordbank, generates a collection of unique words in the wordbank
   * 
   * @return a collection of words
   */
  public Collection<String> getDocument(){
    Collection<String> s = new HashSet<String>();
    getDocument(s, root);
    return s;
  }

  public void getDocument(Collection<String> s, Node n){
    if(n==null) return;
    s.add(n.word);
    getDocument(s, n.left);
    getDocument(s, n.right);
  }

  /**
   * Node class holds the value of a word, the word itself and how many of that word exist in the wordbank.
   * 
   * It also stores its relations to other nodes in the implemented BST, that is sorted based on word values.
   * 
   * Has accessor and mutator methods for value.
   */
  public class Node{
    long value;
    String word;
    int count;
    Node parent = null;
    Node left = null;
    Node right = null;

    Node(long value, String word){
      this.value=value;
      this.word=word;
      this.count=1;
    }

    Node(long value, String word, int count){
      this.value=value;
      this.word=word;
      this.count=count;
    }

    public long getValue(){
      return this.value;
    }

    public void setValue(long value){
      if(value>=0) this.value = value;
    }
  }

  /**
   * Pair class stores values of a word and its value
   * It implements the Comparable interface which compares the pairs long value to another.
   * This is my favourite class.
   */
  public class Pair implements Comparable<Pair>{
    String word;
    long value;

    Pair(String word, long value){
      this.word = word;
      this.value = value;
    }

    public int compareTo(Pair p){
      return Long.compare(this.value,p.value);
    }
  }

  /*
   * Method gives a visual representation of the BST as a String
   * very awesome
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
    int keyWidth = n.word.length();
    // Make a string of leftWidth spaces
    String leftPadding = " ".repeat(leftWidth);
    String rightPadding = " ".repeat(rightWidth);
    String midPadding = " ".repeat(keyWidth);
    result.add(leftPadding + n.word + rightPadding);
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
}