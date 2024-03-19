package cosc201.lec08;

import java.util.Arrays;
import java.util.Random;

/**
 * An illustration of merge sort that prints out the process as
 * you go - slightly refined from PrintingMerge
 */
public class PrintingMergeRefined{

  private static final String PADDING = "  ";
  static StringBuilder prefix = new StringBuilder();

  public static void mergeSort(int[] a) {
    mergeSort(a, 0, a.length);
  }

  public static void mergeSort(int[] a, int lo, int hi) {
    report("Sorting between " + lo + " and " + hi);
    report("Current order: " + valueString(a, lo, hi));
    if (hi - lo <= 1) {
      report("Base case");
      return;
    }
    int mid = (hi + lo)/2;
    report("Splitting at " + mid);
    extendPrefix();
    mergeSort(a, lo, mid);
    mergeSort(a, mid, hi);
    shortenPrefix();
    merge(a, lo, mid, hi);
  }

  private static void extendPrefix() {
    prefix.append(PADDING);
  }

  private static void shortenPrefix() {
    prefix.delete(prefix.length() - PADDING.length(), prefix.length());
  }

  private static String valueString(int[] a, int lo, int hi) {
    StringBuilder result = new StringBuilder();
    for(int i = lo; i < hi; i++) {
      result.append(a[i]);
      result.append(' ');
    }
    return result.toString();
  }

  public static void merge(int[] a, int lo, int mid, int hi) {

    report("Merging from " + lo + " to " + mid + " to " + hi);
    report("Current order first part:  " + valueString(a, lo, mid));
    report("Current order second part: " + valueString(a, mid, hi));
    int[] t = new int[hi-lo];
    int li = lo, ri = mid, ti = 0;
  
    while (li < mid && ri < hi) {
      if (a[li] <= a[ri]) t[ti++] = a[li++];
      else                t[ti++] = a[ri++];
    }
  
    while (li < mid) t[ti++] = a[li++];
    while (ri < hi ) t[ti++] = a[ri++];  
  
    System.arraycopy(t, 0, a, lo, hi-lo);
    report("Merged order: " + valueString(a, lo, hi));

  }

  public static void report(String message) {
    System.out.println(prefix + message);
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