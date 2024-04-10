package cosc201.tut05;

/**
 * An extended verison of the "draw an hourglass from a word" program. This
 * allows for removing different numbers of characters from the left and right
 * hand ends of
 * the word. In this version, to do that you must provide a word as the first
 * command line argument, followed by either one or two integers. If you provide
 * one integer, that number of characters will be removed from both ends of the
 * word. If you provide two integers, the first will be removed from the left
 * and the second from the right.
 * 
 * Negative arguments (and non-integers) are not allowed and cause exceptions.
 * 
 * Developed using the help of (and sometimes obstruction by) code copilot.
 * 
 * @author Michael Albert
 * @version 1.0 (28/3/2024)
 * 
 */
public class Hourglass {

  static String word = "hourglass";
  static int l = 1;
  static int r = 1;

  public static void main(String[] args) {
    if (args.length > 0) {
      word = args[0];
    }
    if (args.length == 2) {
      try {
        l = Integer.parseInt(args[1]);
        r = l;
        if (l < 0)
          throw new NumberFormatException();
      } catch (NumberFormatException e) {
        System.err.println("Invalid number argument: " + args[1]);
        System.exit(1);
      }
    }
    if (args.length > 2) {
      try {
        l = Integer.parseInt(args[1]);
        r = Integer.parseInt(args[2]);
        if (l < 0 || r < 0)
          throw new NumberFormatException();
      } catch (NumberFormatException e) {
        System.err.println("Invalid number argument(s): " + args[1] + " " + args[2]);
        System.exit(1);
      }
    }
    drawHourglass(word, l, r);
  }

  private static void drawHourglass(String word, int l, int r) {
    int toRemove = l + r;
    int i = 0;
    while (i * toRemove < word.length()) {
      System.out.println(removeAndPad(word, i * l, i * r));
      i++;
    }
    i -= 2;
    while (i >= 0) {
      System.out.println(removeAndPad(word, i * l, i * r));
      i--;
    }

  }

  /**
   * Remove the first l and last r characters from a string, pad on the
   * left with l spaces and on the right with r spaces.
   * 
   * @param word the string to remove characters from
   * @param l    the number of characters to remove from the left
   * @param r    the number of characters to remove from the right
   * @return the string with the first l and last r characters removed
   */

  public static String removeAndPad(String word, int l, int r) {
    return " ".repeat(l) + word.substring(l, word.length() - r) + " ".repeat(r);
  }
}
