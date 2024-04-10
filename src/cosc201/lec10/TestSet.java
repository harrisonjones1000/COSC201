package cosc201.lec10;

public class TestSet {

  public static void main(String[] args) {
    BasicSet<String> s = new BasicSet<>();
    System.out.println("Adding cat " + s.add("cat"));
    System.out.println("Adding dog " + s.add("dog"));
    System.out.println("Adding cat again " + s.add("cat"));
    System.out.println("The set: " + s);
    System.out.println("s contains cat: " + s.contains("cat"));
    System.out.println("s contains eel: " + s.contains("eel"));
    System.out.println("s contains " + s.size() + " elements");


  }

}
