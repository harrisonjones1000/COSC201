package cosc201.lab05;

public class BSTExample {
  public static void main(String[] args) {
    BST t = new BST();
    args = new String[] {"5", "6", "7", "8", "9"};
    for(String s : args) {
      t.add(s);
    }
    //System.out.println(t);
    //System.out.println("Alternatively");

    System.out.println(t.altToString());
    System.out.println("Done");

    System.out.println(t.height("5"));
    System.out.println(t.height("6"));
    System.out.println(t.height("7"));
    System.out.println(t.height("8"));
    System.out.println(t.height("9"));

    t.delete("8");
    System.out.println(t.altToString());
    System.out.println("Done");

    System.out.println(t.height("5"));
    System.out.println(t.height("6"));
    System.out.println(t.height("7"));
    System.out.println(t.height("9"));


    
  }
}
