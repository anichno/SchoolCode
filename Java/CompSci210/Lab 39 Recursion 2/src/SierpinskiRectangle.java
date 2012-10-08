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

public class SierpinskiRectangle {

  public static final int WIDTH = 512;
  public static final int HEIGHT = 512;
  public static final int DELAY = 10;   // milliseconds
  public static final Color RECTANGLE_COLOR = Color.BLUE;
  public static final Color BACKGROUND_COLOR = Color.WHITE;

  public static void main(String[] args) {

    DrawingPanel p = new DrawingPanel(WIDTH, HEIGHT);
    Graphics g = p.getGraphics();
    p.setBackground(BACKGROUND_COLOR);

    // draw single outer rectangle
    
    g.setColor(BACKGROUND_COLOR);    
    int x = 0;
    int y = 0;
    int width = WIDTH;
    int height = HEIGHT;
    g.fillRect(x, y, width, height);
    p.copyGraphicsToScreen();

    // fill outer triangle with Sierpinski inner rectangles 
    
    g.setColor(RECTANGLE_COLOR);
    fillInnerRectangle(p, g, x, y, width, height);
  }

  private static void fillInnerRectangle(DrawingPanel p, Graphics g, int x, int y, int width, int height) {
    
    // recursive case: inner triangle is at least 3 pixels wide
    // base case: inner triangle is less than 3 pixels wide (do nothing)
    if (width - x >= 3) {
      System.out.println("hi");

      int inner_x = x + width/3;
      int inner_y = y + height/3;
      int inner_width = width/3;
      int inner_height = height/3;
      g.fillRect(inner_x, inner_y, inner_width, inner_height);
      p.copyGraphicsToScreen();
      p.sleep(DELAY);

      // fill top left inner triangle
      
//      int top_left_rectangle_x = x/3;
//      int top_left_rectangle_y = y/3;
//      int top_left_rectangle_width = width/3;
//      int top_left_rectangle_height = height/3;
//
//      fillInnerRectangle(p, g, top_left_rectangle_x, top_left_rectangle_y, top_left_rectangle_width, top_left_rectangle_height);

      // fill top right inner triangle
      
      int top_right_rectangle_x = width + x;
      int top_right_rectangle_y = y/3;
      int top_right_rectangle_width = width/3;
      int top_right_rectangle_height = height/3;

      fillInnerRectangle(p, g, top_right_rectangle_x, top_right_rectangle_y, top_right_rectangle_width, top_right_rectangle_height);
//
//      // fill top left inner triangle
//      
//      int[] top_left_triangle_x = {(x[0] + x[1]) / 2, x[1], (x[1] + x[2]) / 2};
//      int[] top_left_triangle_y = {(y[0] + y[1]) / 2, y[1], (y[1] + y[2]) / 2};
//
//      fillInnerTriangle(p, g, top_triangle_x, top_triangle_y);
//      
//      // fill top right inner triangle
//      
//      int[] top_right_triangle_x = {(x[0] + x[1]) / 2, x[1], (x[1] + x[2]) / 2};
//      int[] top_right_triangle_y = {(y[0] + y[1]) / 2, y[1], (y[1] + y[2]) / 2};
//
//      fillInnerTriangle(p, g, top_triangle_x, top_triangle_y);
    }
  }
}
