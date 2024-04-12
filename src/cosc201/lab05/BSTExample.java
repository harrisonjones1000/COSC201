package cosc201.lab05;

public class BSTExample {
  public static void main(String[] args) {
    BST t = new BST();
    for(String s : args) {
      t.add(s);
    }
    System.out.println(t);
    System.out.println("Alternatively");
    System.out.println(t.altToString());
    System.out.println("Done");
  }
}
