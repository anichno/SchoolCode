<<<<<<< HEAD
/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Oct 19, 2011 at 7:14:03 PM
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

public class Constructor_fun {

  public static void main(String[] args) {
    DrawingPanel panel = new DrawingPanel(300,300);
    Graphics2D g = panel.getGraphics();
    panel.setBackground(Color.white);
    
    Line line1 = new Line(0,0,150,150);
    System.out.println(line1);
    line1.draw(g);
    
    Point p1 = new Point(50,50);
    Point p2 = new Point(50,217);
    Line line2 = new Line(p1, p2);
    System.out.println(line2);
    line2.setColor(Color.red);
    line2.draw(g);
    
    Line line3 = new Line();
    line3.setX1(0);
    line3.setY1(250);
    line3.setX2(150);
    line3.setY2(150);
    System.out.println(line3);
    g.drawLine(line3.getX1(), line3.getY1(), line3.getX2(), line3.getY2());
    
    Line line4 = new Line(100, 50, 200, 100);
    line4.setColor(Color.green);
    line4.setWidth(20);
    line4.draw(g);
    
    Line line5 = new Line(200, 200, 280, 200);
    line5.setColor(Color.red);
    line5.setWidth(10);
    line5.draw(g);
    
    panel.copyGraphicsToScreen();
  }
}

class Line {
  
  private int x1 = 0;
  private int y1 = 0;
  private int x2 = 0;
  private int y2 = 0;
  private Color lineColor = Color.black;
  private int width = 1;
  
  public Line() {
  }
  
  public Line(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }
  
  public Line(Point p1, Point p2) {
    x1 = p1.x;
    y1 = p1.y;
    x2 = p2.x;
    y2 = p2.y;
  }
  
  public void draw(Graphics2D g) {
    Color oldColor = g.getColor();
    Stroke oldStroke = g.getStroke();
    g.setColor(lineColor);
    g.setStroke(new BasicStroke(width));
    g.drawLine(x1, y1, x2, y2);
    g.setColor(oldColor);
    g.setStroke(oldStroke);
  }
  
  
  public void setColor(Color newColor) {
    lineColor = newColor;
  }
  
  public void setWidth(int width) {
    this.width = width;
  }
  
  public int getX1() {
    return x1;
  }
  
  public void setX1(int x1) {
    this.x1 = x1;
  }
  
  public int getY1() {
    return y1;
  }
  
  public void setY1(int y1) {
    this.y1 = y1;
  }
  
  public int getX2() {
    return x2;
  }
  
  public void setX2(int x2) {
    this.x2 = x2;
  }
  
  public int getY2() {
    return y2;
  }
  
  public void setY2(int y2) {
    this.y2 = y2;
  }
  
  @Override
  public String toString() {
    return "(" + x1 + ", " + y1 + ") (" + x2 + ", " + y2 + ")";
  }
}
=======
/**
 * Draws cool lines with objects
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Oct 19, 2011 at 7:14:03 PM
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

public class Constructor_fun {

  public static void main(String[] args) {
    DrawingPanel panel = new DrawingPanel(300,300);
    Graphics2D g = panel.getGraphics();
    panel.setBackground(Color.white);
    
    Line line1 = new Line(0,0,150,150);
    System.out.println("Line 1: " + line1);
    line1.draw(g);
    
    Point p1 = new Point(50,50);
    Point p2 = new Point(50,250);
    Line line2 = new Line(p1, p2);
    System.out.println("Line 2: " + line2);
    line2.setWidth(3);
    line2.setColor(Color.red);
    line2.draw(g);
    
    Line line3 = new Line();
    line3.setX1(0);
    line3.setY1(300);
    line3.setX2(150);
    line3.setY2(150);
    System.out.println("Line 3: " + line3);
    g.drawLine(line3.getX1(), line3.getY1(), line3.getX2(), line3.getY2());
    
    Line line4 = new Line(100, 50, 200, 100);
    line4.setColor(Color.green);
    line4.setWidth(20);
    line4.draw(g);
    
    Line line5 = new Line(200, 200, 280, 200);
    line5.setColor(Color.red);
    line5.setWidth(10);
    line5.draw(g);
    
    Line line6 = new Line();
    line6.setColor(Color.blue);
    line6.setWidth(6);
    line6.draw(g);
    
    panel.copyGraphicsToScreen();
  }
}

class Line {
  
  private int x1 = 0;
  private int y1 = 0;
  private int x2 = 0;
  private int y2 = 0;
  private Color lineColor = Color.black;
  private int width = 1;
  
  public Line() {
    this(new Point (0,150), new Point (300,150));
  }
  
  public Line(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }
  
  public Line(Point p1, Point p2) {
    x1 = p1.x;
    y1 = p1.y;
    x2 = p2.x;
    y2 = p2.y;
  }
  
  public void draw(Graphics2D g) {
    Color oldColor = g.getColor();
    Stroke oldStroke = g.getStroke();
    g.setColor(lineColor);
    g.setStroke(new BasicStroke(width));
    g.drawLine(x1, y1, x2, y2);
    g.setColor(oldColor);
    g.setStroke(oldStroke);
  }
  
  
  public void setColor(Color newColor) {
    lineColor = newColor;
  }
  
  public void setWidth(int width) {
    this.width = width;
  }
  
  public int getX1() {
    return x1;
  }
  
  public void setX1(int x1) {
    this.x1 = x1;
  }
  
  public int getY1() {
    return y1;
  }
  
  public void setY1(int y1) {
    this.y1 = y1;
  }
  
  public int getX2() {
    return x2;
  }
  
  public void setX2(int x2) {
    this.x2 = x2;
  }
  
  public int getY2() {
    return y2;
  }
  
  public void setY2(int y2) {
    this.y2 = y2;
  }
  
  @Override
  public String toString() {
    return "(" + x1 + ", " + y1 + ") (" + x2 + ", " + y2 + ")";
  }
}
>>>>>>> ebfadc50ebde76a9555af67f2f2c62e210788558
