/**
 * A Point object represents a location in a Cartesian coordinate system.
 *
 * @author C14Anthony.Canino
 *
 * @version 1.0   Fall 2011
 */
public class Point {

  private double radius;
  private double angle;

  /**
   * Default constructor. Creates a point at the origin.
   */
  public Point() {
    this(0, 0);
  }

  /**
   * Constructor that creates a point at the specified location.
   * 
   * @param x The x coordinate.
   * @param y The y coordinate.
   */
  public Point(int x, int y) {
    setXY(x,y);
  }

  /**
   * Constructor that creates a duplicate point from the specified point.
   * 
   * @param p The point to duplicate.
   */
  public Point(Point p) {
    this(p.getX(), p.getY());
  }

  private int polar2grid(char which) {
    int x = (int) Math.round((radius * Math.cos(angle)));
    int y = (int) Math.round((radius * Math.sin(angle)));
    if (which == 'x')
      return x;
    else if (which == 'y')
      return y;
    else
      return 0;
  }
  
  /**
   * Return the current value of the x coordinate.
   * 
   * @return the x coordinate.
   */
  public int getX() {
    return polar2grid('x');
  }

  /**
   * Return the current value of the y coordinate.
   * 
   * @return the y coordinate.
   */
  public int getY() {
    return polar2grid('y');
  }

  /**
   * Change the x coordinate of the point to the specified value.
   * 
   * @param newX The new value for the x coordinate.
   */
  public void setX(int newX) {
    radius = Math.sqrt( newX*newX + getY()*getY() );
    angle = Math.atan2( getY(), newX );
  }

  /**
   * Change the y coordinate of the point to the specified value.
   * 
   * @param newY The new value for the y coordinate.
   */
  public void setY(int newY) {
    radius = Math.sqrt( getX()*getX() + newY*newY );
    angle = Math.atan2( newY, getX() );
  }

  /**
   * Change the x and y coordinates of the point to the specified values.
   * 
   * @param newX The new value for the x coordinate.
   * @param newY The new value for the y coordinate.
   */
  public void setXY(int x, int y) {
    radius = Math.sqrt( x*x + y*y );
    angle = Math.atan2( y, x );
  }

  /**
   * Convert the Point object to a string representation.
   * 
   * @return a string representation of the Point object.
   */
  public String toString() {
    return "(" + getX() + "," + getY() + ")";
  }

  /**
   * Calculate the distance between this Point and another Point.
   * 
   * @param other The other point used in the distance calculation.
   * 
   * @return The distance between the two points.
   */
  public double distance(Point other) {
    int dx = getX() - other.getX();
    int dy = getY() - other.getY();
    return Math.sqrt(dx * dx + dy * dy);
  }

  /**
   * Calculate the distance of the point from the origin, (0,0).
   * 
   * @return  Returns the distance from the origin, (0,0).
   */
  public double distanceFromOrigin() {
    return Math.sqrt(getX() * getX() + getY() * getY());
  }

  /**
   * Calculate the angle of the Point from the origin
   * 
   * @return  Returns the angle between of the point with the origin in
   *          the range -pi to +pi.
   */
  public double angle() {
    return Math.atan2(getY(), getX());
  }

  /**
   * Shifts this point's location by the given amount.
   * 
   * @param dx How much to shift the x coordinate
   * @param dy How much to shift the y coordinate
   */
  public void translate(int dx, int dy) {
    //x += dx;
    //setX(getX()+dx);
    //y += dy;
    //setY(getY()+dy);
    setXY(getX()+dx, getY()+dy);
  }
  
  public double getRadius() {
    return radius;
  }
  
  public double getAngle() {
    return angle;
  }
}
