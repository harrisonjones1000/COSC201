package cosc201.lec09;

import java.util.Arrays;

/**
 * An array based max-heap implementation (for illustrative purposes only).
 * First implementation, 2011, modifications through 2023.
 * 
 *  @author Michael Albert
 *
 */
public class ArrayHeap<T extends Comparable<T>>{

  private static final int DEFAULT_CAPACITY = 31;
  private static final int NO_VIOLATION = -1;

  private T[] heap;
  private int size = 0;

  @SuppressWarnings("unchecked")
  public ArrayHeap(int capacity){
    heap = (T[]) new Comparable[capacity];
  }
  
  public ArrayHeap(){
    this(DEFAULT_CAPACITY);
  }
  
  private void enlarge() {
    heap = Arrays.copyOf(heap, 2*heap.length+1);
  }

  /**
   * Peek at the largest element of the heap (don't make any changes).
   * @return The largest element of the heap.
   */
  public T get() {
    return heap[0];
  }

  /**
   * Determine if the heap contains any elements
   * @return true if the heap contains no elements
   */
  public boolean isEmpty() {
    return size <= 0;
  }

  /**
   * Return the size of the heap
   * @return the size of the heap
   */
  public int size() {
    return size;
  }

  /**
   * Add an element to the heap. The element is initially added in the first 'free' location
   * (at position 'size'), after expanding capacity if needed. Then that element is allowed to
   * float up the branch from it to the root of the heap whenever there's a violation of the condition
   * that a parent node is meant to be greater than a child node.
   * 
   * @param element the element to be added.
   */
  public void add(T element) {
    if (size == heap.length) enlarge();
    int childIndex = size;
    size++;
    heap[childIndex] = element;
    int parentIndex = (childIndex - 1)/2;
    while (childIndex > 0 && heap[childIndex].compareTo(heap[parentIndex]) > 0) {
      swap(childIndex, parentIndex);
      childIndex = parentIndex;
      parentIndex = (childIndex-1)/2;
    }
  }

  /**
   * Remove the largest element from the heap. That element is at position 0. To remove it,
   * replace it with the element in the last occupied position (at position 'size - 1'). Then
   * let that element sink to fix any heap violations -- to do this it needs to be exchanged with
   * the larger of its children if either child is larger than it.
   * 
   * @return The largest element of the heap.
   */
  public T remove() {

    T result = heap[0];
    heap[0] = heap[size - 1];
    size--;
    int parentIndex = 0;
    do{
      int childIndex = getLargerChildIndex(parentIndex);
      if (childIndex == NO_VIOLATION) break; // exits the loop
      swap(childIndex, parentIndex);
      parentIndex = childIndex;
    } while (true); // Exits always via break above.
    return result;
  }

  private void swap(int i, int j) {
    T temp = heap[i];
    heap[i] = heap[j];
    heap[j] = temp;
  }

  private int getLargerChildIndex(int i) {
    int l = 2*i + 1;
    int r = 2*i + 2;
    if ( r < size && // Both children exist
      heap[r].compareTo(heap[i]) > 0 &&  // right child is too big
      heap[r].compareTo(heap[l]) > 0 // right child is bigger than left child
      ) {
     return r; 
    }
    // At this point we know that either the right child doesn't exist or it
    // does and creates no violation, or it does and creates a violation but
    // the left child is the same size or bigger. So if there is a violation then
    // the left child is the larger child
    if ( l < size && // Left child exists and
      heap[l].compareTo(heap[i]) > 0 // there is a violation.
      ) {
      return l;
    }
    // At this point there can be no violation either because no children exist
    // or because they're both smaller than the parent.k
    return NO_VIOLATION;  
  }

  public String toString() {
    StringBuilder result = new StringBuilder("Heap: ");
    for(int i = 0; i < size; i++) {
       result.append(heap[i].toString() + " ");
    }
    return result.toString();
  }
}