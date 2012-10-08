/**
 * Point.java
 * Description: A Point object represents a pair of (x, y) coordinates.
 *
 * @author C14Anthony.Canino
 *
 * @version 1.0   Fall 2011
 */

public class Point
{
  private int x;
  private int y;

  //-------------------------------------------------------------------
  /**
   * Return the distance the point is from the origin, (0,0)
   * 
   * @return  Return the distance the point is from the origin, (0,0)
   */
  public double distanceFromOrigin()
  {
    return Math.sqrt(x * x + y * y);
  }

  
  //-------------------------------------------------------------------
  /**
   * Shifts this point's location by the given amount.
   * 
   * @param dx How much to shift the x coordinate
   * @param dy How much to shift the y coordinate
   */
  public void translate(int dx, int dy)
  {
    x += dx;
    y += dy;
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  public void setX(int x) {
    this.x = x;
  }
  
  public void setY(int y) {
    this.y = y;
  }
  
  public void setXY(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  @Override
  public String toString() {
    String pointInfo = "(" + x + ", " + y + ")";
    return pointInfo;
  }
  
  public double distance(Point p) {
    double distance = Math.sqrt((x-p.getX())*(x-p.getX())+(y-p.getY())*(y-p.getY()));
    return distance;
  }
}

