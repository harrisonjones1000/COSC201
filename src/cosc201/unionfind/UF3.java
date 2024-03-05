package cosc201.unionfind;

import java.util.Arrays;

/**
 * The third union-find implementation using local representatives augmented
 * by a rank function.
 *
 * Time for union is big-Theta of time for find.
 * Time for find is Theta(log n) in worst case.
 *
 * @author Michael Albert
 */
public class UF3 implements UnionFind {

  int[] reps; // Now just 'immediate' representative.
  int[] rank; // The rank, i.e., length of longest chain below a representative
  private int groups;

  /**
   * Constructs a new uninitialised union-find instance using ranks.
   * 
   */
  public UF3() {
  }

  /**
   * Makes a new set of singletons (erases all current information).
   * 
   * @param n the number of singletons
   */
  @Override
  public void make(int n) {
    reps = new int[n];
    rank = new int[n];
    for (int i = 0; i < n; i++) {
      reps[i] = i;
      rank[i] = 0;
    }
    groups = n;
  }

  /**
   * Finds the representative of a group
   * 
   * @param x an element of the union-find structure
   * @return the representative for the element x
   */
  @Override
  public int find(int x) {
    while (x != reps[x]) {
      x = reps[x];
    }
    return x;
  }

  /**
   * Join the groups containing two elements. The new representative is
   * the previous representative of larger rank. This ensures that the rank
   * increases only if the two ranks are equal. If the ranks are equal, the
   * representative of the second element is the new representative.
   * 
   * @param x the first element
   * @param y the second element
   */
  @Override
  public void union(int x, int y) {
    rootUnion(find(x), find(y));
  }

  @Override
  public int size() {
    return reps.length;
  }

  @Override
  public String toString() {
    return "UF3" + Arrays.toString(reps);
  }

  private void rootUnion(int x, int y) { // x and y are known to be representatives
    if (x == y) {
      return;
    }
    groups--;
    if (rank[x] > rank[y]) {
      reps[y] = x;
    } else if (rank[y] > rank[x]) {
      reps[x] = y;
    } else { // ranks are equal
      reps[x] = y;
      rank[y]++;
    }
  }

  @Override
  public int groups() {
    return groups;
  }

  public String name() {
    return "UF3";
  }

}
