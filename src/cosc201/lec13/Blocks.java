package cosc201.lec13;

import java.util.Random;

// How big is the largest block in a hash table using simple linear probing?

public class Blocks {

  static final Random R = new Random();
  public static void main(String[] args) {
    int n = 100000;
    int trials = 10;
    for(int trial = 0; trial < trials; trial++) {
    boolean[] occupied = new boolean[n];
    int gap = 10000;
    for(int i = 0; i <= (8*n)/10; i++) {
      int bucket = R.nextInt(n);
      while (occupied[bucket]) {
        bucket = (bucket + 1) % n;
      }
      occupied[bucket] = true;
      if (i > 0 && (i % gap == 0)) {
        System.out.print(maxChain(occupied) + " ");
      }
    }
    System.out.println();
  }
  }

  static int maxChain(boolean[] buckets) {
    int result = 0;
    int current = 0;
    for (int i = 0; i < buckets.length; i++) {
      if (buckets[i]) {
        current++;
      } else {
        if (current > result) {
          result = current;
        }
        current = 0;
      }
    }
    int startBlock = 0;
    int endBlock = 0;
    int i = 0;
    while (i < buckets.length && buckets[i]) {
      startBlock++;
      i++;
    }
     i = buckets.length - 1;
    while (i >= 0 && buckets[i]) {
      endBlock++;
      i--;
    }
    if (endBlock + startBlock > result) result = endBlock + startBlock;
    return result;
  }
  
}
