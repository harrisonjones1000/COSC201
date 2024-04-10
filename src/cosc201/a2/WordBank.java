package cosc201.a2;

import java.util.Collection;
import java.util.Set;

/**
 * An interface for a word bank that stores words and their values. For this
 * prototype implementation words must consist of Strings of lowercase 
 * letters a-z. The value of a word is a non-negative long integer.
 * 
 * While the implementation will not be tested for efficiency, to obtain full
 * marks all operations that manipulate or access the contents of the WordBank 
 * should execute in expected constant time per word involved. This might be 
 * input in the case of add or remove operations, or output in the case of get 
 * operations.
 * 
 * You will submit an implementation of this interface -- that is, a class that
 * implements the WordBank interface.
 * 
 * @author Michael Albert
 * @version 1.0 (2024-04)
 * 
 */
public interface WordBank {

  public static final long NOT_FOUND = -1;

  /**
   * Returns the value associated with the word, or NOT_FOUND if the word is not
   * in the bank.
   * 
   * @param word A word whose value is to be determined
   * @return The word's value or NOT_FOUND
   */
  public long getValue(String word);

  /**
   * Add a word to the bank with the given value. If the word is not valid, or
   * the value is negative throw an IllegalArgumentException.
   * 
   * @param word A word to be added to the bank
   * @param value Its value
   */
  public void addWord(String word, long value);

  /**
   * Add a collection of words to the bank with the given value. Ignore any
   * invalid words but if the value is negative throw an 
   * IllegalArgumentException.
   * 
   * @param words A collection of words to be added to the bank
   * @param value Their common value
   */
  public void addWords(Collection<String> words, long value);

  /**
   * Remove a word from the bank. If the word is invalid or not in
   * the bank, do nothing.
   * 
   * @param word A word to be removed from the bank
   */
  public void removeWord(String word);

  /**
   * Remove a collection of words from the bank. Ignore any words that are not
   * in the bank.
   * 
   * @param words The collection of words to remove
   */
  public void removeWords(Collection<String> words);

  /**
   * Return the words in the bank of the given value.
   * 
   * @param value The value to be searched for.
   * @return The words in the bank of that value.
   */
  public Set<String> getWords(long value);

  /**
   * Return the words in the bank with values in the range lowValue inclusive to
   * highValue exclusive.
   * 
   * @param lowValue The lowest value (inclusive)
   * @param highValue The highest value (exclusive)
   * @return The words in the bank with values in the range lowValue to highValue.
   */
  public Set<String> getWords(long lowValue, long highValue);

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
  public String getMaximumWord(Collection<String> words);

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
  public String getMinimumWord(Collection<String> words);

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
  public String getMedianWord(Collection<String> words);

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
  public String getMeanWord(Collection<String> words);

  /**
   * Return the value of a document, considered as a collection of words. The
   * value of a document is the sum of the values of all the distinct words 
   * it contains, meaning that invalid words or words not in the bank are 
   * ignored, as are the second and subsequent occurrences of a word.
   * 
   * @param words A collection of words
   * @return The value of the document
   */
  public long getDocumentValue(Collection<String> words);

}