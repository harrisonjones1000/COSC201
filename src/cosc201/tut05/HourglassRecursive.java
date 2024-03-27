package cosc201.tut05;

public class HourglassRecursive {

  /**
   * A recursive version of the "draw an hourglass from a word" program.
   * Why? Because we can!
   * 
   * With help and occasional obstruction from code copilot.
   * 
   * @author Michael Albert
   * @version 1.0 (28/3/2024)
   */

  static String word = "hourglass";

  public static void main(String[] args) {
    if (args.length > 0) {
      word = args[0];
    }
    drawHourglass("", word);
  }

  /** 
   * A recursive version of the method that draws an hourglass. In a recursive case we
   * print the word with a prefix, then call the method again with the first and last
   * characters removed from the word and the prefix extended by one character. Then
   * we print the word with the prefix again.
   * 
   * The base cases are when the word has length 0 or 1. In the first case we do nothing,
   * in the second case we print the prefix followed by the word.
   * 
   * @param prefix the prefix to add to the word
   * @param word the word to draw the hourglass from
   * 
   */
  private static void drawHourglass(String prefix, String word) {
    
    // Base cases
    if (word.length() == 0) return;

    if (word.length() == 1) {
      System.out.println(prefix + word);
      return;
    }

    // Recursive case
    System.out.println(prefix + word);
    drawHourglass(prefix + " ", word.substring(1, word.length() - 1));
    System.out.println(prefix + word);
  }
  
}
