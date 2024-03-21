

import java.util.Random;
import cosc201.utilities.Timer;

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
    /* 
    double egg = 0;
    int k = 100000;
    for(int i = 0; i<k; i++){
      egg += sum(1000)/(sum(100));
    }
    
    System.out.println(egg/k);

    Exp1 Q1. typically gives a ratio 9.44
    Should be 10
    */

    double egg = 0;
    int k = 75;
    int n = 10;
    while(egg<10){
      for(int i = 0; i<k; i++){
        egg += sum(10*n)/sum(n);
      }
      egg = egg/k;
      System.out.println(egg);
      n=2*n;
      System.out.println(n);
    }
    System.out.println(egg);
  }
  
  
  static long sum(int n) {
    double result = 0;
    Timer t = new Timer();
    t.start();
    for(int i = 0; i < n; i++){
      result += R.nextDouble();
    }
    return t.stop();
  }
  
}
