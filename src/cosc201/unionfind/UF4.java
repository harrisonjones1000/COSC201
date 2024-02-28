package cosc201.unionfind;

import java.util.Arrays;

/**
 * The fourth union-find implementation using local representatives augmented
 * by a rank function and path compression.
 *
 * Time for union is big-Theta of time for find.
 * Time for find is O(log n) in worst case (in fact much better)
 *
 * @author Michael Albert
 */
public class UF4 implements UnionFind {

  int[] reps; // Now just 'immediate' representative.
  int[] rank; // The rank, i.e., length of longest chain below a representative
  private int groups;

  /**
   * Constructs a new uninitialised union-find instance using ranks and
   * path compression.
   * 
   */

  public UF4() {
  };

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
   * Finds the representative of a group and compresses the path to the
   * representative.
   * 
   * @param x an element of the union-find structure
   * @return the representative for the element x
   */
  @Override
  public int find(int x) {
    if (x != reps[x]) {
      reps[x] = find(reps[x]);
    }
    return reps[x];
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
  public String toString() {
    return "UF4" + Arrays.toString(reps);
  }

  @Override
  public int size() {
    return reps.length;
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
    } else {
      reps[x] = y;
      rank[y]++;
    }
  }

  @Override
  public int groups() {
    return groups;
  }

  public String name() {
    return "UF4";
  }
}
