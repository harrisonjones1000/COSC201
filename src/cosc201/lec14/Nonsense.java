package cosc201.lec14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Nonsense {

  static final char END_OF_WORD = '$';
  private static final Random R = new Random();
  static StringBuilder firstLetters = new StringBuilder();
  static HashMap<Character, StringBuilder> followingLetters = new HashMap<>();

  static {
    try {
      Scanner in = new Scanner(new File("/Users/MichaelAlbert/Documents/Work/Teaching/2024/201/cosc201code/src/cosc201/lec14/words.txt"));
      for(char c = 'a'; c <= 'z'; c++) {
        followingLetters.put(c, new StringBuilder());
      }
      while(in.hasNextLine()) {
        String word = in.nextLine();
        firstLetters.append(word.charAt(0));
        updateFollowing(word);
      }
      in.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static void updateFollowing(String word) {
    for(int i = 0; i < word.length()-1; i++) {
      char c = word.charAt(i);
      char n = word.charAt(i+1);
      followingLetters.get(c).append(n);
    }
    char last = word.charAt(word.length()-1);
    followingLetters.get(last).append(END_OF_WORD);
  }

  private static String randomWord() {
    StringBuilder result = new StringBuilder();
    // Pick the first letter and append it to the result
    char c = firstLetters.charAt(R.nextInt(firstLetters.length()));
    result.append(c);
    // While the word is not finished, pick a next letter and append it to the result
    while (c != END_OF_WORD) {
      StringBuilder afterC = followingLetters.get(c);
      c = afterC.charAt(R.nextInt(afterC.length()));
      result.append(c);
    }
    result.deleteCharAt(result.length()-1); // Trim end of word character from end.
    return result.toString();
  }

  private static String randomString(int length) {
    StringBuilder result = new StringBuilder();
    for(int i = 0; i < length; i++) {
      result.append((char) ('a' + R.nextInt(26)));
    }
    return result.toString();
  }

  public static void main(String[] args) {
    System.out.println("Fully random 6 character strings");
    for(int i = 0; i < 10; i++) System.out.println(randomString(6));
    System.out.println();
    System.out.println("Random words");
    for(int i = 0; i < 10; i++) System.out.println(randomWord());
  }
  
}
