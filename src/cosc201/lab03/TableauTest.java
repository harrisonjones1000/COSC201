package cosc201.lab03;

/**
 * A partial test framework for the tableaux methods. You should add your
 * own tests! In particular, how about a method that checks and displays 
 * to avoid all the copy pasting I did!
 *
 * @author Michael Albert
 */
public class TableauTest {

  public static void main(String[] args) {
    int[][] t = new int[][] {
      {1, 4, 5, 10, 11},
      {2, 6, 8},
      {3, 9, 12},
      {7}  
    };
    
    System.out.println(TableauChecker.toString(t));
    System.out.println("The next five should be true");
    System.out.println(TableauChecker.rowLengthsDecrease(t));
    System.out.println(TableauChecker.rowValuesIncrease(t));
    System.out.println(TableauChecker.columnValuesIncrease(t));
    System.out.println(TableauChecker.isSetOf1toN(t));
    System.out.println(TableauChecker.isTableau(t));
    
    t = new int[][] {};
    
    System.out.println(TableauChecker.toString(t));
    System.out.println("The next five should be true (empty tableau)");
    System.out.println(TableauChecker.rowLengthsDecrease(t));
    System.out.println(TableauChecker.rowValuesIncrease(t));
    System.out.println(TableauChecker.columnValuesIncrease(t));
    System.out.println(TableauChecker.isSetOf1toN(t));
    System.out.println(TableauChecker.isTableau(t));
    
    t = new int[][] {
        {1, 3, 5},
        {4, 7}
      };
      
    System.out.println(TableauChecker.toString(t));
    System.out.println("Should be three true, then two false");
    System.out.println(TableauChecker.rowLengthsDecrease(t));
    System.out.println(TableauChecker.rowValuesIncrease(t));
    System.out.println(TableauChecker.columnValuesIncrease(t));
    System.out.println(TableauChecker.isSetOf1toN(t));
    System.out.println(TableauChecker.isTableau(t));
    
  }
 
}