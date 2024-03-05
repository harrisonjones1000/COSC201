package cosc201.lec03;
import cosc201.utilities.Timer;

import java.util.Random;

import cosc201.unionfind.*;

public class Lec03 {

  public static void main(String[] args) {
    UnionFind uf1 = new UF1();
    UnionFind uf2 = new UF2();
    UnionFind uf3 = new UF3();
    int n = Integer.parseInt(args[0]);
    randomTest(uf1, n);
    randomTest(uf2, n);
    randomTest(uf3, n);
    sequenceTest(uf1, n);
    sequenceTest(uf2, n);
    sequenceTest(uf3, n);
  }
  
  public static void randomTest(UnionFind u, int n) {
    Random R = new Random();
    int[] x = new int[n];
    int[] y = new int[n];
    for(int i = 0; i < n; i++) {
      x[i] = R.nextInt(n);
      y[i] = R.nextInt(n);
    }
    Timer t = new Timer();
    t.start();
    u.make(n);
    for(int i = 0; i < n; i++) {
      u.union(x[i],y[i]);
    }
    t.stop();
    System.out.println(n + " random unions with " + u.name() + " in " + t.getTimeInMs() + "ms");
  }

  public static void sequenceTest(UnionFind u, int n) {
    Timer t = new Timer();
    t.start();
    u.make(n);
    for(int i = 0; i < n; i++) {
      u.union(0, i);
    }
    t.stop();
    System.out.println(n + " sequential unions with " + u.name() + " in " + t.getTimeInMs() + "ms");
  }
  
}
