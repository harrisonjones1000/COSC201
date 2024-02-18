package cosc201.a1;

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

  /**
   * Constructs a BaseSimulator object with the specified base.
   * 
   * @param base the base to simulate
   */
  public BaseSimulator(Base base) {
    this.base = base;
  }

  /**
   * Simulate the effect of opening the airlock doors of the module at the given index. 
   * If the index is out of bounds, this should have no effect.
   * @param index the index of a module
   */
  public void open(int index) {
    // TODO: Implement this method
  }

  /**
   * Determine whether the module at the given index is safe. If the index is out of
   * bounds, this should return false (since it refers to the ocean)
   * @param index the index of a module
   * @return true of the module is safe, false otherwise
   */
  public boolean safe(int index) {
    // TODO: Implement this method
    return false;
  }

  /**
   * Determine whether the entire base is safe. The base is safe if at least one of 
   * its modules is safe.
   * @return true if the base is safe, false otherwise
   */
  public boolean safe() {
    // TODO: Implement this method
    return false;
  }

  
}
