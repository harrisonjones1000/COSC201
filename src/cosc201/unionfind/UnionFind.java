package cosc201.unionfind;

/**
 * Interface for classes supporting dynamic set partitions (union-find). Make
 * is included only for completeness since interfaces can't require 
 * constructors.
 * 
 * @author Michael Albert
 */
public interface UnionFind {
  
  /**
   * Makes a new set of n singletons.
   * @param n The number of singletons.
   */
  public void make(int n);
  
  /**
   * Finds the representative of an element.
   * @param x the element
   * @return its representative
   */
  public int find(int x);
  
  /**
   * Forms the union of the sets containing two elements.
   * @param x the first element.
   * @param y the second element.
   */
  public void union(int x, int y);

  /**
   * Returns the size of the underlying set of this union-find instance.
   * @return the size of the underlying set
   */
  public int size();

  /**
   * Returns the number of groups in this union-find instance.
   * @return the current number of groups
   */
  public int groups();

  /**
   * Returns the name of the union-find algorithm used.
   * @return the algorithm's name
   */
  public String name();
 
 
  
}
