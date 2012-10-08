
/**
 * Final Exam: Problem 6     Limit your time to approximately 23 minutes
 *
 * Grade: _____ / 25
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Prob06 {

  public static final int WINDOW_X = 300;
  public static final int WINDOW_Y = 300;

  public static void main(String[] args) {
    DrawingPanel panel = new DrawingPanel(WINDOW_X, WINDOW_Y);
    panel.setBackground(Color.white);
    Graphics g = panel.getGraphics();

    Line test1 = new Line();
    Line test2 = new Line(30, 50, 80, 150, Color.MAGENTA);
    Line test3 = new Line(new Point(100, 150), new Point(200, 20), Color.blue);

    System.out.println("Line 1 is " + test1.getLength() + " long.");
    System.out.println(test1);
    test1.draw(g);

    test2.translate(20, 0);
    test2.draw(g);

    test3.setColor(Color.black);
    test3.draw(g);

    panel.copyGraphicsToScreen();
  }
}

//=====================================================================
// Super class to use for problem 6
abstract class Shape {

  private Color color;

  public Shape() {
    this(Color.RED);
  }

  public Shape(Color color) {
    setColor(color);
  }

  public Color getColor() {
    return new Color(color.getRGB()); // return a copy of the color
  }

  public void setColor(Color newColor) {
    if (newColor != null) {
      color = new Color(newColor.getRGB()); // make a local copy of the color
    }
  }

  public abstract void draw(Graphics g);

  public abstract void translate(int dx, int dy);
}

//=====================================================================
// Create your Line class below
class Line extends Shape {

  private Point point1;
  private Point point2;

  public Line() {
    this(new Point(0, 0), new Point(50, 50), Color.red);
  }

  public Line(int x1, int y1, int x2, int y2, Color color) {
    this(new Point(x1, y1), new Point(x2, y2), color);
  }

  public Line(Point point1, Point point2, Color color) {
    super(color);
    this.point1 = point1;
    this.point2 = point2;
  }

  public double getLength() {
    return Math.sqrt((square(point1.getX() - point2.getX()))
                     + square(point1.getY() - point2.getY()));
  }

  private double square(double num) {
    return num * num;
  }

  @Override
  public void draw(Graphics g) {
    g.setColor(getColor());
    g.drawLine((int) point1.getX(), (int) point1.getY(), (int) point2.getX(), (int) point2.getY());
  }

  @Override
  public void translate(int dx, int dy) {
    point1.translate(dx, dy);
    point2.translate(dx, dy);
  }

  public String toString() {
    return "X1: " + point1.getX() + "\r\n"
           + "Y1: " + point1.getY() + "\r\n"
           + "X2: " + point2.getX() + "\r\n"
           + "Y2: " + point2.getY() + "\r\n"
           + "Color: " + getColor();
  }
}