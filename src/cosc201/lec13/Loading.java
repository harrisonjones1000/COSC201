package cosc201.lec13;

import java.util.Random;

/**
 * Quick investigation of how the maximum bucket size in a hash table using
 * chaining changes based on the load factor.
 * 
 * @author Michael Albert
 * @date 2023-04-27
 * @version 1.0
 * 
 */
public class Loading {

  static final Random R = new Random();

  public static void main(String[] args) {
    int n = 100;
    int trials = 10;
    int gap = 10;
    if (args.length > 0) {
      n = Integer.parseInt(args[0]);
    }
    if (args.length > 1) {
      gap = Integer.parseInt(args[1]);
    }

    for (int trial = 0; trial < trials; trial++) {
      int maxSize = 0;
      int[] sizes = new int[n];
      for (int i = 0; i < 2 * n; i++) {
        int bucket = R.nextInt(n);
        sizes[bucket]++;
        if (sizes[bucket] > maxSize) {
          maxSize = sizes[bucket];
        }
        if (i > 0 && (i % gap == 0)) {
          System.out.print(maxSize + " ");
        }
      }
      System.out.println();
    }

  }

}