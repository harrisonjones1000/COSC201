package cosc201.lec03;
import cosc201.utilities.Timer;
import cosc201.unionfind.*;

public class Lec03 {

  public static void main(String[] args) {
    UnionFind uf1 = new UF1();
    UnionFind uf2 = new UF2();
    UnionFind uf3 = new UF3();
    int n = Integer.parseInt(args[0]);
    test(uf1, n);
    test(uf2, n);
    test(uf3, n);
  }
  
  public static void test(UnionFind u, int n) {
    Timer t = new Timer();
    t.start();
    u.make(n);
    for(int i = 0; i < n; i++) {
      int x = (int)(Math.random() * n);
      int y = (int)(Math.random() * n);
      u.union(x,y);
    }
    t.stop();
    System.out.println(u.name() + " " + t.getTimeInMs());
  }
  
}
