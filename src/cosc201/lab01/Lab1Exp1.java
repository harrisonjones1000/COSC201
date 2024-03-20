package cosc201.lab01;

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
    double t = 0;
    int n = 100;
    for(int i = 0; i < n; i++){
      t += timedSum(1000)/timedSum(100);
    }
    System.out.println(t/n);

  }
  
  
  static double sum(int n){
    double result = 0;
    for(int i = 0; i < n; i++){ 
      result += R.nextDouble();
    }
    return result;
  }
  
  static double timedSum(int n) {
    Timer timer = new Timer();
    timer.start();
    double result = sum(n);
    return timer.stop(); 
  }
  
}
