package cosc201.lec14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Represents good strategies in the 'grab the last candy' game.
 * 
 */
public class CandyStrategy {

  static final Random R = new Random();
  
  static final HashMap<String, String> louiseMoves = new HashMap<>();
  static final HashMap<String, String> richardMoves = new HashMap<>();

  static {
    louiseMoves.put("", null); // Louise has no good move from the empty string
    richardMoves.put("", null); // Richard has no good move from the empty string
  }

  /**
   * Returns a good move for Louise, if one exists, from the given position.
   * 
   * @param position The position
   * @return A position that Louise should move to in order to win (null if she has no good move)
   */
  public static String getLouiseMove(String position) {
    if (!louiseMoves.containsKey(position)) { // We don't already know the answer
      computeLouiseMove(position); // So we work it out
    }
    return louiseMoves.get(position); // And return it
  }
  /**
   * Returns a good move for Richard, if one exists, from the given position.
   * 
   * @param position The position
   * @return A position that Richard should move to in order to win (null if she has no good move)
   */
  public static String getRichardMove(String position) {
    if (!richardMoves.containsKey(position)) {
      computeRichardMove(position);
    }
    return richardMoves.get(position);
  }

  /**
   * Computes and stores a good move for Louise from the given position if one exists
   * @param position
   */
  private static void computeLouiseMove(String position) {
    for(String s : allLouiseMoves(position)) { // For every move she could make
      if (getRichardMove(s) == null) { // If it leads to a position where Richard has no good move
        louiseMoves.put(position, s); // Then it's a good move and we can record it.
        return;
      }
    }
    louiseMoves.put(position, null); // No move for Louise is good :(
    return;
  }

  private static void computeRichardMove(String position) {
    for(String s : allRichardMoves(position)) {
      if (getLouiseMove(s) == null) {
        richardMoves.put(position, s);
        return;
      }
    }
    richardMoves.put(position, null);
    return;
  }

  /**
   * Computes all the moves Louise can make from a given position.
   * 
   * @param position The position
   * @return A list of the strings that Louise can leave after her move.
   */
  private static ArrayList<String> allLouiseMoves(String position) {
    ArrayList<String> result = new ArrayList<>();
    char leftEnd = position.charAt(0);
    for(int i = position.length()-1; i >= 0; i--) { // Look at Louise's "big" moves first
      if (position.charAt(i) == leftEnd) {
        result.add(position.substring(i+1)); //Louise removes from the beginning to position i inclusive
      }
    }
    return result;
  }

  private static ArrayList<String> allRichardMoves(String position) {
    ArrayList<String> result = new ArrayList<>();
    char rightEnd = position.charAt(position.length()-1);
    for(int i = 0; i < position.length(); i++) {
      if (position.charAt(i) == rightEnd) {
        result.add(position.substring(0,i)); // Richard removes from the end to position i inclusive.
      }
    }
    return result;
  }

  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < 2000; i++) {
      sb.append((char) ('A' + R.nextInt(10)));
    }
    String s = sb.toString();
    System.out.println("Initial position: " + s);
    System.out.println("Louise moves to: " + getLouiseMove(s));
    System.out.println("Richard moves to: " + getRichardMove(s));
    System.out.println(louiseMoves.size() + "  " + richardMoves.size());
  }


  
  
}
