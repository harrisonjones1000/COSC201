package cosc201.lec10;

/**
 * A simple set implementation using an array.
 * 
 * Developed using the help of code copilot from prototype code
 * in COSC201 lecture.
 * 
 * Note this does not conform to the Java Set interface nor should
 * it be considered as a useful or practical class -- it's just to
 * illustrate the concepts.
 * 
 * @param <T> the type of elements in the set
 
 */
public class BasicSet<T> {

    public static final int DEFAULT_CAPACITY = 10;
    private static final int NOT_FOUND = -1;
    
    private T[] storage;
    private int size;
    
    public BasicSet() {
      this(DEFAULT_CAPACITY);
    }
    
    @SuppressWarnings("unchecked")
    public BasicSet(int capacity) {
      storage = (T[]) new Object[capacity];
      this.size = 0;
    }

    public boolean isEmpty() {
      return size == 0;
    }  
    
    public int size() {
      return size;
    }
    
    public boolean add(T t) {
      if (this.contains(t)) return false;
      if (size == storage.length) increaseCapacity();
      storage[size] = t;
      size++;
      return true;    
    }
  
    public boolean contains(T t) {
      return this.getIndex(t) != NOT_FOUND;
    }
    
    public boolean remove(T t) {
      int i = this.getIndex(t);
      if (i == NOT_FOUND) return false;
      storage[i] = storage[size-1];
      size--;
      return true;
    }
    
    private int getIndex(T t) {
      for(int i = 0; i < size; i++) {
        if (storage[i].equals(t)) return i;
      }
      return NOT_FOUND;
    }
    
    private void increaseCapacity() {
      @SuppressWarnings("unchecked")
      T[] newStorage = (T[]) new Object[storage.length * 2];
      for(int i = 0; i < size; i++) {
        newStorage[i] = storage[i];
      }
      storage = newStorage;
    }

    public String toString() {
      StringBuilder result = new StringBuilder();
      result.append("{");
      for(int i = 0; i < size; i++) {
        result.append(storage[i]);
        if (i < size - 1) result.append(", ");
      }
      result.append("}");
      return result.toString();
    }

  
  
  }

