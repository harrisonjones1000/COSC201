package cosc201.a1;
import cosc201.unionfind.*;
import cosc201.utilities.*;

/**
 * The BaseSimulator class simulates the effect of opening the airlock doors of a base.
 * 
 * @author Michael Albert (2024-02)
 * @version 1.0
 * 
 * Some code based on suggestions from code copilot.
 */
public class BaseSimulator {

  private Base base;
  private int size;
  private UnionFind uf;
  private Timer t = new Timer();
  public double time = 0;

  /**
   * Constructs a BaseSimulator object with the specified base.
   * 
   * @param base the base to simulate
   */
  public BaseSimulator(Base base) {
    this.base = base;
    this.size = base.getCols()*base.getRows();
    uf = new UF3();
    uf.make(this.size+1);
  }

  /**
   * Simulate the effect of opening the airlock doors of the module at the given index. 
   * If the index is out of bounds, this should have no effect.
   * @param index the index of a module
   */
  public void open(int index){
    if(0<index && index<=size){ //if valid index
      for(int i: base.getNeighbours(index)){
        uf.union(index,i); //adjacent modules into same group
      }
    }
  }

  /**
   * Determine whether a module or a group of modules for a given index are safe. If the index is out of
   * bounds, this should return false (since it refers to the ocean)
   * @param index the index of a module
   * @return true of the module is safe, false otherwise
   */
  public boolean safe(int index){
    if(!(0<index && index<=size)){
      return false; //if ocean, not safe
    }

    for(int i: base.getNeighbours(index)){
      if(uf.find(i)==uf.find(index)) return false; //not closed 
    }

    for(int i: base.getNeighbours(index)){ //if an adjacents adjacent is flooded, opening all of adjacent floods module, not safe
      for(int k: base.getNeighbours(i)){
        if(uf.find(k)==uf.find(0)){
          return false;
        } 
      }
    }
    return true;
  }
  
  /**
   * Determine whether the entire base is safe. The base is safe if at least one of 
   * its modules is safe.
   * @return true if the base is safe, false otherwise
   */
  public boolean safe(){
    //t.start();
    for(int i=1; i <=size; i++){
      if(safe(i)){
        //time += t.stop();
        return true;
      }
    }
    //time += t.stop();
    return false;
  }
}
