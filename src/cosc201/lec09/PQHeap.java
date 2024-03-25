package cosc201.lec09;

/**
 * A priority queue implementation using a heap.
 * 
 * @author Michael Albert (31/3/2023)
 */
public class PQHeap<T> {

  private ArrayHeap<HeapNode<T>> heap;

  public PQHeap() {
    heap = new ArrayHeap<HeapNode<T>>();
  }

  public void add(T item, int priority) {
    heap.add(new HeapNode<T>(item, priority));
  }

  public T remove() {
    return heap.remove().getItem();
  }

  public int size() {
    return heap.size();
  }

  public String toString() {
    return heap.toString();
  }

  public boolean isEmpty() {
    return heap.isEmpty();
  }

  private class HeapNode<T> implements Comparable<HeapNode<T>> {

    private T item;
    private int priority;

    public HeapNode(T item, int priority) {
      this.item = item;
      this.priority = priority;
    }

    public int compareTo(HeapNode<T> other) {
      return this.priority - other.priority;
    }

    public T getItem() {
      return item;
    }
  
  }

  
  
}
