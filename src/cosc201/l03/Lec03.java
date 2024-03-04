package cosc201.l03;
import cosc201.utilities.Timer;
import cosc201.unionfind.*;

public class Lec03 {

  public static void main(String[] args) {
    UnionFind uf1 = new UF1();
    UnionFind uf2 = new UF2();
    test(uf1, 10000);
    test(uf2, 10000);
  }
  
  public static void test(UnionFind u, int n) {
    Timer t = new Timer();
    t.start();
    u.make(n);
    for(int i = 0; i < n; i++) {
      int x = (int)(Math.random() * n);
      int y = (int)(Math.random() * n);
      u.union(x, y);
    }
    t.stop();
    System.out.println(u.name() + " " + t.getTimeInMs());
  }
  
}
