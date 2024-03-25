package cosc201.lec09;

import java.util.Arrays;

/**
 *  Some demo code for heap operations
 * 
 *  @author Michael Albert
 *
 */
public class HeapDemo{

  public static void main(String[] args) {
    ArrayHeap<String> h = new ArrayHeap<>();
    String[] as = new String[] {"yak", "boa",
      "ant", "roc", "eel", "kea"};
    for(String a : as) h.add(a);
    h.remove(); h.remove();
    System.out.println(h);

  }
}