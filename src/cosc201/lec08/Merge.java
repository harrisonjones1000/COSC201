package cosc201.lec08;

import java.util.Arrays;
import java.util.Random;

public class Merge{

  public static void mergeSort(int[] a) {
    mergeSort(a, 0, a.length);
  }

  public static void mergeSort(int[] a, int lo, int hi) {
    if (hi - lo <= 1) return;
    int mid = (hi + lo)/2;
    mergeSort(a, lo, mid);
    mergeSort(a, mid, hi);
    merge(a, lo, mid, hi);
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
      a = new int[] {4, 6, 2, 1, 5, 7, 0, 8, 16};
    } else {
      Random R = new Random();    
      int n = Integer.parseInt(args[0]);
      a = new int[n];
      for(int i = 0; i < n; i++) a[i] = R.nextInt(1000);
    }
    System.out.println(Arrays.toString(a));
    mergeSort(a);
    System.out.println(Arrays.toString(a));
  }

}