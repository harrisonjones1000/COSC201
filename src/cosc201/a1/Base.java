package cosc201.a1;

/**
 * The Base class represents a base with a grid of rows and columns.
 * 
 * @author Michael Albert (2024-02)
 * @version 1.0
 * 
 * Some code based on suggestions from code copilot.
 */
public class Base {
   
  public static final int OCEAN = 0;
  private int rows;
  private int cols;

  /**
   * Constructs a Base object with the specified number of rows and columns.
   * 
   * @param rows the number of rows in the base
   * @param cols the number of columns in the base
   */
  public Base(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
  }

  /**
   * Constructs a square Base object with the specified size.
   * 
   * @param size the number of rows and columns in the base
   */
  public Base(int size) {
      this(size, size);
  }

  /** A getter for rows.
   * @return the number of rows in the base
   */
  public int getRows() {
    return rows;
  }

  /** A getter for columns.
   * @return the number of columns in the base
   */
  public int getCols() {
    return cols;
  }
    
  /**
   * Returns the index of the specified row and column in the base.
   * If the row or column is out of bounds, returns OCEAN.
   * 
   * @param row the row index
   * @param col the column index
   * @return the index of the specified row and column, or OCEAN if out of bounds
   */
  public int index(int row, int col) {
    if (row < 0 || row >= rows || col < 0 || col >= cols) {
      return OCEAN;
    }
    return 1 + row * cols + col;
  }

  /**
   * Returns the neighbours of the cell at the given index.
   * @param index the given index
   * @return the indices of its neighbours
   */
  public int[] getNeighbours(int index) {
    int row = (index - 1) / cols;
    int col = (index - 1) % cols;
    return getNeighbours(row, col);
  }
  
  /**
   * Returns an array of the indices of the neighbors of a given cell.
   * 
   * @param row the row index of the cell
   * @param col the column index of the cell
   * @return an array of indices of the neighbors of the cell, including the ocean
   */
  public int[] getNeighbours(int row, int col) {
    int[] result = new int[4];
    result[0] = index(row - 1, col);
    result[1] = index(row, col + 1);
    result[2] = index(row + 1, col);
    result[3] = index(row, col - 1);
    return result;
  }

  
}