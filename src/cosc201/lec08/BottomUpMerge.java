package cosc201.lec08;

import java.util.Arrays;
import java.util.Random;

public class BottomUpMerge{

  static final int MAX_VALUE = 1000; // For randomly-generated examples

  public static void mergeSort(int[] a) {
    int blockSize = 1;
    while (blockSize < a.length) {
      // System.out.println(Arrays.toString(a));
      int lo = 0;
      while (lo + blockSize < a.length) {
        int hi = lo + 2*blockSize;
        if (hi > a.length) hi = a.length;
        merge(a, lo, lo + blockSize, hi);
        lo = hi;
      }
      // System.out.println("Merged blocks of size " + blockSize);
      blockSize *= 2; 
    }
    // System.out.println(Arrays.toString(a));
  }

  public static void merge(int[] a, int lo, int mid, int hi) {

    int[] t = new int[hi-lo];
    int li = lo, ri = mid, ti = 0;
  
    while (li < mid && ri < hi) {
      if (a[li] <= a[ri]) t[ti++] = a[li++];
      else                t[ti++] = a[ri++];
    }
  
    while (li < mid) t[ti++] = a[li++];
    while (ri < hi ) t[ti++] = a[ri++];  
  
    System.arraycopy(t, 0, a, lo, hi-lo);

  }
  
  public static void main(String[] args) {
    int[] a;
    if (args.length == 0) {
      a = new int[] {4, 6, 16, 2, 5, 7, 8, 0, 1};
    } else {
      Random R = new Random();    
      int n = Integer.parseInt(args[0]);
      a = new int[n];
      for(int i = 0; i < n; i++) a[i] = R.nextInt(MAX_VALUE);
    }
    System.out.println(Arrays.toString(a));
    mergeSort(a);
    System.out.println(Arrays.toString(a));
  }

}