package cosc201.lab05;

public class BSTExample {
  public static void main(String[] args) {
    BST t = new BST();
    args = new String[] {"dog", "bat", "ant", "cat", "emu", "rat", "moa", "tod", "zebra", "yak"};
    for(String s : args) {
      t.add(s);
    }

    System.out.println(t.altToString() + "\n");
    System.out.println(t.next("rat"));

  }
}
