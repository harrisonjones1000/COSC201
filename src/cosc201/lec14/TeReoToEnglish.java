package cosc201.lec14;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class TeReoToEnglish {

    static HashMap<String, ArrayList<String>> dictionary = new HashMap<>();

    static {
      try {
        Scanner in = new Scanner(new File("/Users/MichaelAlbert/Documents/Work/Teaching/2024/201/cosc201code/src/cosc201/lec14/TeReoToEnglish.txt"));
        while(in.hasNextLine()) {
          String[] word = in.nextLine().split(",",2);
          String tr = word[0];
          String en = word[1];
          if (!dictionary.containsKey(tr)) {
            dictionary.put(tr, new ArrayList<String>());
          }
          dictionary.get(tr).add(en);
        }
        in.close();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
  
    public static void main(String[] args) {
      String tr = args[0];
      System.out.println("Looking up " + tr);
      if (!dictionary.containsKey(tr)) {
        System.out.println("I'm sorry, I don't know that word yet.");
      } else {
        System.out.println("Possible meanings:");
        for (String en : dictionary.get(tr)) {
          System.out.println("-- " + en);
        }
      }
    }

    /**
     * Looks up a word in te reo and returns its English equivalent(s) if possible.
     * @param tr A word in te reo.
     * @return Its English equivalents (or null if not known)
     */
    public static ArrayList<String> lookup(String tr) {
      if (!dictionary.containsKey(tr)) {
        return null;
      }
      return dictionary.get(tr);
    }
}
