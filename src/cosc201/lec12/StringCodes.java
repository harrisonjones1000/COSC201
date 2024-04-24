package cosc201.lec12;

/**
 * A simple application class to display the hash codes of its arguments.
 * 
 * @author Michael Albert
 * @version 1.0 (2024-04)
 */
public class StringCodes{

  /**
   * Main method to display the hash codes of its arguments.
   * @param args the arguments
   */
  public static void main(String[] args) {
    for(String s : args) {
      System.out.println(s + " " + s.hashCode());
    }
  }

}