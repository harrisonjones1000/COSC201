package cosc201.lab03;

/**
 * Skeleton code for checking whether arrays represent Young tableaux.
 *
 * @author Michael Albert
 */
public class TableauChecker {

    /**
     * Determines whether the array passed to it is a valid tableau or not.
     *
     * @param t a two-dimensional array to test for tableau-ness.
     *
     * @return true if the parameter is a valid tableau, otherwise false
     */
    public static boolean isTableau(int[][] t){
        return false;
    }

    /**
     *  Returns a string representation of an array based tableau.
     *
     * @param t a two-dimensional array which represents a tableau.
     *
     * @return a string representation of an array based tableau.
     */
    public static String toString(int[][] t) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                result.append(String.format("%-4s", t[i][j]));
            }
            if (i < t.length-1) {
                result.append("\n");
            }
        }
        return result.toString();
    }
    
    
    public static boolean rowLengthsDecrease(int[][] t) {
      return false;
    }
    
    public static boolean rowValuesIncrease(int[][] t) {
      return false;
    }
    
    public static boolean columnValuesIncrease(int[][] t) {
      return false;
    }
    
    public static boolean isSetOf1toN(int[][] t) {
      return false;
    }

    
}
