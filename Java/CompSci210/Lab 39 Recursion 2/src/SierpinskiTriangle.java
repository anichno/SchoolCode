/**
 * File: SierpinskiTriangle.java
 *
 * Description: Draws a Sierpinski Triangle.
 * http://en.wikipedia.org/wiki/Sierpinski_triangle
 * 
 * This version uses a DrawingPanel, arrays of x,y coordinates,
 * and the fillPolygon method in the Graphics class.
 *
 * @author Randall.Bower
 *
 * Created: Dec 6, 2011, 4:25:31 PM
 */
import java.awt.Color;
import java.awt.Graphics;

public class SierpinskiTriangle {

  public static final int WIDTH = 512;
  public static final int HEIGHT = 512;
  public static final int DELAY = 10;   // milliseconds
  public static final Color TRIANGLE_COLOR = Color.BLUE;
  public static final Color BACKGROUND_COLOR = Color.WHITE;

  public static void main(String[] args) {

    DrawingPanel p = new DrawingPanel(WIDTH, HEIGHT);
    Graphics g = p.getGraphics();
    p.setBackground(BACKGROUND_COLOR);

    // draw single outer triangle (points up)
    
    g.setColor(TRIANGLE_COLOR);    
    int[] x = {0, WIDTH / 2, WIDTH}; // (x1,y1) = bottom left vertex
    int[] y = {HEIGHT, 0, HEIGHT};   // (x2,y2) = top center vertex
    g.fillPolygon(x, y, 3);          // (x3,y3) = bottom right vertex
    p.copyGraphicsToScreen();

    // fill outer triangle with Sierpinski inner triangles 
    
    g.setColor(BACKGROUND_COLOR);
    fillInnerTriangle(p, g, x, y);
  }

  private static void fillInnerTriangle(DrawingPanel p, Graphics g, int[] x, int[] y) {
    
    // recursive case: inner triangle is at least 3 pixels wide
    // base case: inner triangle is less than 3 pixels wide (do nothing)
    
    if (x[ 2] - x[ 1] >= 3) {
      
      // draw trigangle inside center of outter triangle (points down)
      
      int[] inner_triangle_x = {(x[0] + x[1]) / 2, (x[1] + x[2]) / 2, x[1]};
      int[] inner_triangle_y = {(y[0] + y[1]) / 2, (y[1] + y[2]) / 2, y[0]};

      g.fillPolygon(inner_triangle_x, inner_triangle_y, 3);
      p.copyGraphicsToScreen();
      p.sleep(DELAY);

      // fill lower left inner triangle
      
      int[] left_triangle_x = {x[0], (x[0] + x[1]) / 2, (x[0] + x[2]) / 2};
      int[] left_triangle_y = {y[0], (y[0] + y[1]) / 2, (y[0] + y[2]) / 2};

      fillInnerTriangle(p, g, left_triangle_x, left_triangle_y);

      // fill lower right inner triangle
      
      int[] right_triangle_x = {(x[0] + x[2]) / 2, (x[1] + x[2]) / 2, x[2]};
      int[] right_triangle_y = {(y[0] + y[2]) / 2, (y[1] + y[2]) / 2, y[2]};

      fillInnerTriangle(p, g, right_triangle_x, right_triangle_y);

      // fill top center inner triangle
      
      int[] top_triangle_x = {(x[0] + x[1]) / 2, x[1], (x[1] + x[2]) / 2};
      int[] top_triangle_y = {(y[0] + y[1]) / 2, y[1], (y[1] + y[2]) / 2};

      fillInnerTriangle(p, g, top_triangle_x, top_triangle_y);
    }
  }
}
