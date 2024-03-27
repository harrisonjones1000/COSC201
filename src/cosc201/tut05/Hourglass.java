package cosc201.tut05;

public class Hourglass {

    static String word = "hourglass";

    /**
     * The main method for the Hourglass class. Prints the command
     * line argument in hourglass shape (or uses "hourglass" by default
     * if no command line argument given.)
     * 
     * Developed using the help of code copilot in COSC201 tutorial.
     * 
     * @param args
     */
    public static void main(String[] args) {
        if (args.length > 0) {
          word = args[0];
        }
        drawHourglass(word);
    }

    /**
     * Remove the first k and last k characters from a string
     * 
     * @param word the string to remove characters from
     * @param k the number of characters to remove
     * @return the string with the first k and last k characters removed
     */
    public static String remove(String word, int k) {
        return word.substring(k, word.length() - k);
    }

    /** Prepend k spaces to a string
     * 
     * @param word the string to prepend spaces to
     */
    public static String prependSpaces(String word, int k) {
        // for(int i = 0; i < k; i++) {
        //     spaces += " ";
        // }
        return " ".repeat(k) + word; // Java 11 (repeat)
      }

    public static void drawHourglass(String word) {
        int i = 0;
        while (2*i < word.length()) {
            System.out.println(prependSpaces(
              remove(word, i), i));
            i++;
        }
        i -= 2; // readjust the length to delete
        while (i >= 0) {
            System.out.println(prependSpaces(
              remove(word, i), i));
            i--;
        }
    }
  
}
