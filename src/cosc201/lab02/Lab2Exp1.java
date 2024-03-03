package cosc201.lab02;

/**
 * Lab 2, Experiment 1
 * 
 * a. Look at the times required to compute fibRec(n) for various values of n.
 *    How do these times behave? How large a value of n can you (realistically)
 *    apply this method to?
 * b. Do the other implementations produce the same sequence of values? Aside
 *    from "running the code and checking" are you confident of this (based
 *     on reading the code)?
 * c. How large a value of n can you apply the other methods to in a
 *    reasonable length of time? What's the limiting condition?
 * d. Which of the other methods is "best"?
 * 
 * @author Michael Albert
 */
public class Lab2Exp1 {

  public static void main(String[] args) {
    // Add your code for doing experiments here.
  }
   
  static long fibRec(int n) {
    if (n <= 1) return 1;
    return fibRec(n-1) + fibRec(n-2);
  }
  
  static long fibA(int n) {
    long a = 1;
    long b = 1;
    for(int i = 0; i < n; i++) {
      long c = a + b;
      a = b;
      b = c;
    }
    return a;
  }
  
  static long fibB(int n) {
    long[] result = new long[n+1];
    result[0] = 1;
    result[1] = 1;
    for(int k = 2; k <= n; k++) {
      result[k] = result[k-1] + result[k-2];
    }
    return result[n];
  }
  
  static long fibC(int n) {
    return fibC(1, 1, n);
  }
  
  static long fibC(int a, int b, int n) {
    if (n == 0) return a;
    return fibC(b, a+b, n-1);
  }
  
}
