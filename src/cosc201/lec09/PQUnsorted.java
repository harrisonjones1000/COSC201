package cosc201.lec09;

import java.util.NoSuchElementException;

// Basic priority queue implementation for elements of generic type.
// Uses an unsorted array, so to remove must find the maximum index
// save the value there, and move the previous 'last' element to that 
// position. Add justs add at the end (enlarging capacity if needed).
public class PQUnsorted<T> {

  static final int CAPACITY = 10;
  
  @SuppressWarnings("unchecked")
  T[] items = (T[]) new Object[CAPACITY];
  int[] priorities = new int[CAPACITY];
  int size = 0;
  
  public PQUnsorted() {};
  
  public void add(T item, int priority) {
    if (size == items.length) enlarge();
    items[size] = item;
    priorities[size] = priority;
    size++;
  }
  
  public T remove() {
    if (size == 0) {
      throw new NoSuchElementException("Remove from empty priority queue");
    }
    int i = maxIndex();
    T result = items[i];
    size--;
    // Move the previous last element into position i
    items[i] = items[size];
    priorities[i] = priorities[size];
    return result;
  }
  
  // Find the index of the maximum priority element
  // By design, this should never be called if the queue is empty
  // so the second line is safe.
  private int maxIndex() {
    int result = 0;
    int maxPriority = priorities[0];
    for(int j = 1; j < size; j++) {
      if (priorities[j] > maxPriority) {
        result = j; maxPriority = priorities[j];
      }
    } 
    return result;
  }
  
  public int size() {
    return size;
  }

  
  @SuppressWarnings("unchecked")
  // Enlarge the arrays holding items and priorities
  private void enlarge() {
    T[] newItems = (T[]) new Object[items.length*2];
    int[] newPriorities = new int[priorities.length*2];
    System.arraycopy(items, 0, newItems, 0, items.length);
    System.arraycopy(priorities, 0, newPriorities, 0, priorities.length);
    items = newItems;
    priorities = newPriorities;
  }
  
  public String toString() {
    StringBuilder result = new StringBuilder("PQUnsorted: ");
    for(int i = 0; i < size; i++) {
      result.append("(");
      result.append(priorities[i]);
      result.append(",");
      result.append(items[i]);
      result.append(") ");
    }
    return result.toString();
  }


}