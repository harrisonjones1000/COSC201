package cosc201.lab03;
import java.util.ArrayList;

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
      boolean p1,p2,p3,p4;
      p1 = rowLengthsDecrease(t);
      p2 = rowValuesIncrease(t);
      p3 = columnValuesIncrease(t);
      p4 = isSetOf1toN(t);

      if(p1&&p2&&p3&&p4){
        return true;
      }
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
    
    
    public static boolean rowLengthsDecrease(int[][] t){
      for(int i = 0; i < t.length-1; i++){
        if(!(t[i].length>=t[i+1].length)){
          return false;
        }
      }
      return true;
    }
    
    public static boolean rowValuesIncrease(int[][] t){
      for(int i = 0; i<t.length; i++){
        for(int j =0; j<t[i].length-1;j++){
          if(!(t[i][j]<=t[i][j+1])){
            return false;
          }
        }
      }
      return true;
    }
    
    public static boolean columnValuesIncrease(int[][] t){
      if(t.length == 0){
        return true;
      }

      int j = 0;
      for(int i = 0; i < t[0].length;i++){
        while(true){
          try{
            if(t[j][i]>t[j+1][i]){
              System.out.println("column value increase " + t[j][i] + " " + t[j+1][i]);
              return false;
            }
            j++;
          }catch(ArrayIndexOutOfBoundsException e){
            break;
          }
        }
      }
      return true;
    }
    
    public static boolean isSetOf1toN(int[][] t){
      int cells = 0;
      ArrayList<Integer> array = new ArrayList<Integer>();
      for(int i = 0; i < t.length; i++){
        cells += t[i].length;
        for(int j = 0; j < t[i].length; j++){
          array.add(t[i][j]);
        }
      }

      for(int k = 1; k < cells; k++){
        if(!array.contains(k)){
          return false;
        }
      }
      return true;
    }
}
