
/**
 * Point class - used as a base case to demonstrate inheritance.
 * 
 * @author Wayne.Brown
 * 
 * @version 1.0 - Oct 30, 2011 at 10:55:07 PM
 */
public class Point {

  private int x;
  private int y;

  /**
   * Default constructor. Creates a point at the origin.
   */
  public Point() {
    this(0, 0);
  }

  /**
   * Constructor - creates a point at the specified (x,y) location.
   * @param x
   * @param y 
   */
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Constructor - creates a point with the same (x,y) coordinates as 
   * the specified point.
   * @param other The exising Point to duplicate.
   */
  public Point(Point other) {
    this(other.x, other.y);
  }

  /**
   * Get the value of the x component.
   * @return 
   */
  public int getX() {
    return x;
  }

  /**
   * Get the value of the y component.
   * 
   * @return the value of the y component.
   */
  public int getY() {
    return y;
  }

  /**
   * Change the value of the x component.
   * 
   * @param newX The new value for the x component.
   */
  public void setX(int newX) {
    x = newX;
  }

  /**
   * Change the value of the y component.
   * 
   * @param newY The new value for the y component.
   */
  public void setY(int newY) {
    y = newY;
  }

  /**
   * Change the point to be at the new specified location.
   * 
   * @param newX The new value for the x component.
   * @param newY The new value for the y component.
   */
  public void setXY(int newX, int newY) {
    x = newX;
    y = newY;
  }

  /**
   * Convert the object to a string representation
   * 
   * @return a string representation of the object
   */
  public String toString() {
    return "(" + x + "," + y + ")";
  }

  /**
   * Calculate the distance between this point and the specified point.
   * 
   * @param other the other point to use for the distance calculations.
   * @return the distance between this point and the specified point.
   */
  public double distance(Point other) {
    int dx = x - other.x;
    int dy = y - other.y;
    return Math.sqrt(dx * dx + dy * dy);
  }

  /**
   * Return the distance the point is from the origin, (0,0)
   * 
   * @return  Return the distance the point is from the origin, (0,0)
   */
  public double distanceFromOrigin() {
    return Math.sqrt(x * x + y * y);
  }

  /**
   * Shifts this point's location by the given amount.
   * 
   * @param dx How much to shift the x coordinate
   * @param dy How much to shift the y coordinate
   */
  public void translate(int dx, int dy) {
    x += dx;
    y += dy;
  }
}