package cosc201.lec09;

import java.util.NoSuchElementException;

// Basic priority queue implementation for elements of generic type.
// Uses an sorted array, so remove just returns the last element.
// Add must shift elements of higher priority along to create space
// for the new element.
public class PQSorted<T> {

  static final int CAPACITY = 10;
  
  @SuppressWarnings("unchecked")
  T[] items = (T[]) new Object[CAPACITY];
  int[] priorities = new int[CAPACITY];
  int size = 0;
  
  public PQSorted() {};
  
  public void add(T item, int priority) {
    if (size == items.length) enlarge();
    int i = size-1;
    while (i >= 0 && priorities[i] > priority) {
      items[i+1] = items[i];
      priorities[i+1] = priorities[i];
      i--;
    }
    items[i+1] = item;
    priorities[i+1] = priority;
    size++;
  }
  
  public T remove() {
    if (size == 0) {
      throw new NoSuchElementException("Remove from empty priority queue");
    }
    size--;
    return items[size];
  }
  
  public int size() {
    return size;
  }
  
  // Find the index of the maximum priority element
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
    StringBuilder result = new StringBuilder("PQSorted:   ");
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