package cosc201.lab01;

import java.util.Random;

/**
 * Lab 1, Experiment 1
 * 
 * a. What is the (typical) ratio between the time taken for sum(1000) 
 *    and sum(100)? Does that make sense? What \textit{should} the ratio be?
 * b. How large do you need to make $n$ before the ratio between the time 
 *    taken for sum(10*n) and sum(n) approaches what it should be? When you
 *    reach that point, how much total time (roughly) is being used?
 * c. What does this say about the design of wall-clock experiments to test 
 *    efficiency?
 * 
 * @author Michael Albert
 */
public class Lab1Exp1 {
  
  static final Random R = new Random();
  
  public static void main(String[] args) {
    System.out.println("haha");
    // Add some code here that evaluates and times sum(n) and sum(10*n) for 
    // (various) n so that you can answer the questions above.
  }
  
  
  static double sum(int n) {
    double result = 0;
    for(int i = 0; i < n; i++) result += R.nextDouble();
    return result;
  }
  
  static double timedSum(int n) {
    // It might be easier to wrap together the timing control and the sum
    // and just return the time taken. Up to you!
    return 0.0; // Just so that this compiles
  }
  
}
