package cosc201.lab01;

import java.util.Random;

/**
 * Lab 1 Experiment 2
 * 
 * The skeleton outline for this experiment includes three methods for
 * constructing a random string of lower case letters. 
 * 
 * a. Read the code for those methods - what are the differences? Which one
 *    would you have written if we'd asked you to?
 * b. Compare the time required by the three methods for various values of n. 
 *    Are they always similar? Remember to make n large enough that 
 *    significant time is required.
 * c. The first method (using basic string concatenation) slows down a lot
 *    as n gets large. Why should that be? What does it say about using string
 *    concatenation in programs in general?
 *
 * @author Michael Albert
 */
public class Lab1Exp2 {

  static final Random R = new Random();

  public static void main(String[] args) {
    // Write and run your experiments here. Remember to use a Timer.    
  }

  static String randString1(int n) {
    String result = "";
    for (int i = 0; i < n; i++) {
      result += randChar();
    }
    return result;
  }

  static String randString2(int n) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < n; i++) {
      result.append(randChar());
    }
    return result.toString();
  }
  
  static String randString3(int n) {
    char[] result = new char[n];
    for (int i = 0; i < n; i++) {
      result[i] = randChar();
    }
    return new String(result);
  }

  static char randChar() {
    return (char) ('a' + R.nextInt(26));
  }

}
