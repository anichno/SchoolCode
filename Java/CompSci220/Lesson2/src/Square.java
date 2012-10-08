
/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Jan 6, 2012 at 9:26:18 AM
 */
public class Square extends Rectangle {
  
  /**
   * Default Constructor, sets sides to 5
   */
  public Square() {
    super(5, 5);
  }
  
  /**
   * Constructor allowing initial setting of sides
   * @param side
   */
  public Square(int side) {
    super(side, side);
  }
  
  /**
   * Setter method for setting the sides of the Square
   * @param side
   */
  public void setSides(int side) {
    super.setSides(side, side);
  }
}
