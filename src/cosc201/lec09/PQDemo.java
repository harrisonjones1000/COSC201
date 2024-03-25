package cosc201.lec09;

public class PQDemo {

  public static void main(String[] args) {
    int n = 5;
    double addProbability = 1.0/3.0; 
    if (args.length > 0) {
      n = Integer.parseInt(args[0]);
    }
    if (args.length > 1) {
      addProbability = Double.parseDouble(args[1]);
    }
    PQHeap<String> pq = new PQHeap<>();
    for (int i = 0; i < n; i++) {
      String item = "Item " + i;
      int priority = (int) (Math.random() * 100);
      System.out.println("Adding " + item + "(" + priority + ")");
      pq.add(item, priority);
    }
    int i = n;
    while (!pq.isEmpty()) {
      System.out.println("Removing " + pq.remove());
      if (Math.random() < addProbability) {
        String item = "Item " + i;
        int priority = (int) (Math.random() * 100);
        System.out.println("Adding " + item + "(" + priority + ")");
        pq.add(item, priority);
        i++;
      }
    }
  }
  
}
