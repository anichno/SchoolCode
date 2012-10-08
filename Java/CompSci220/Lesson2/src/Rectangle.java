
/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Jan 6, 2012 at 9:24:05 AM
 */
public class Rectangle {

  private int side1 = 0;
  private int side2 = 0;
  
  /**
   * Default Constructor, sets sides to 5 and 10
   */
  public Rectangle() {
    side1 = 5;
    side2 = 10;
  }
  
  /**
   * Constructor allowing initial setting of sides
   * @param side1
   * @param side2
   */
  public Rectangle(int side1, int side2) {
    this.side1 = side1;
    this.side2 = side2;
  }
  
  /**
   * Getter method for returning area of shape
   * @return area
   */
  public int getArea() {
    return side1*side2;
  }
  
  /**
   * Setter method for setting the sides of the Rectangle
   * @param side1
   * @param side2
   */
  public void setSides(int side1, int side2) {
    this.side1 = side1;
    this.side2 = side2;
  }
  
  /**
   * Getter method which returns side1
   * @return side1
   */
  public int getSide1() {
    return side1;
  }
  
  /**
   * Getter method which returns side2
   * @return side2
   */
  public int getSide2() {
    return side2;
  }
}
