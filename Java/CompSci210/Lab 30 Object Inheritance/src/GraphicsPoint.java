
/**
 * Class which extends the Point class
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Nov 1, 2011 at 9:59:28 AM
 */
import java.awt.Color;
import java.awt.Graphics2D;

public class GraphicsPoint extends Point {

  private Color color = Color.black;
  private int size = 1;

  public GraphicsPoint(int x, int y) {
    this.setXY(x, y);
  }

  public void setColor(Color newColor) {
    this.color = newColor;
  }

  public Color getColor() {
    return color;
  }

  public void setSize(int newSize) {
    this.size = newSize;
  }

  public int getSize() {
    return size;
  }

  public void drawPoint(Graphics2D g) {
    Color oldColor = g.getColor();
    g.setColor(color);
    g.fillRect(this.getX(), this.getY(), size, size);
    g.setColor(oldColor);
  }

  @Override
  public String toString() {
    return "(" + this.getX() + "," + this.getY()
           + ")\r\nColor: " + color + "\r\nSize: " + size;
  }
}
