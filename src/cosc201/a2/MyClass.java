package cosc201.a2;

import java.util.*;

public class MyClass implements WordBank{
  private Word root = null;
  private static final long NOT_FOUND = -1;

  public MyClass(){}

    /**
   * Returns the value associated with the word, or NOT_FOUND if the word is not
   * in the bank.
   * 
   * @param word A word whose value is to be determined
   * @return The word's value or NOT_FOUND
   */
  public long getValue(String word){
    return getValue(word, root);
  }

  public long getValue(String word, Word n){
    if (n == null) return NOT_FOUND;
    if(n.word.equals(word)) return n.value;

    long l = getValue(word, n.left);
    long r = getValue(word, n.right);

    if(l==NOT_FOUND&&r==NOT_FOUND) return NOT_FOUND;
    if(l==NOT_FOUND) return r;
    return l;
  }

  /**
   * Add a word to the bank with the given value. If the word is not valid, or
   * the value is negative throw an IllegalArgumentException.
   * 
   * @param word A word to be added to the bank
   * @param value Its value
   */
  public void addWord(String word, long value){
    if((isValidWord(word)&&value>=0)||getValue(word)!=NOT_FOUND){
      Word w = new Word(word, value);
      if(root==null){
        root = w;
      }else{
        Word parent = root;
        Word child = root;
        while(child!=null){
          if (Long.compare(child.getValue(), w.getValue())>0){ 
            child = parent.right;
          }else{
            child = parent.left;
          }
        }
        addLink(parent, w);
      }
    }else{
      throw new IllegalArgumentException();
    }
  }

  public void addLink(Word parent, Word child){
    child.parent = parent;
    if(Long.compare(child.getValue(),parent.getValue())>0){
      parent.left = child;
    }else{
      parent.right = child;
    }
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
        while (iterator.hasNext()){
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
  public void removeWord(String word){}

  /**
   * Remove a collection of words from the bank. Ignore any words that are not
   * in the bank.
   * 
   * @param words The collection of words to remove
   */
  public void removeWords(Collection<String> words){}

  /**
   * Return the words in the bank of the given value.
   * 
   * @param value The value to be searched for.
   * @return The words in the bank of that value.
   */
  public Set<String> getWords(long value){
    return new HashSet<String>();
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
    return new HashSet<String>();
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
    return "";
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
    return "";
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
    return "";
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
    return "";
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
    return 0;
  }

  public class Word{
    private String word;
    private long value;
    Word parent = null;
    Word left = null;
    Word right = null;
    int size;

    Word(String word, long value){
      this.word = word;
      this.value=value;
      this.size=1;
    }

    public String getWord(){
      return this.word;
    }

    public long getValue(){
      return this.value;
    }

    public void setWord(String word){
      if(isValidWord(word)) this.word=word;
    }

    public void setValue(long value){
      if(value>=0) this.value = value;
    }
  }
}
