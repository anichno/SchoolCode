import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

public class Triangle extends Shape {

  private Point p1;
  private Point p2;
  private Point p3;
  private Polygon triangle;

  public Triangle(Point p1, Point p2, Point p3, Color color) {
    super(color);
    this.p1 = p1;
    this.p2 = p2;
    this.p3 = p3;
    createPolygon();
    setCenter();
  }

  private void createPolygon() {
    int[] xPoints = new int[]{(int) p1.getX(),
                              (int) p2.getX(), (int) p3.getX()};
    int[] yPoints = new int[]{(int) p1.getY(),
                              (int) p2.getY(), (int) p3.getY()};

    triangle = new Polygon(xPoints, yPoints, 3);
  }

  private void setCenter() {
    int centroidX = (int) (p1.getX() + p2.getX() + p3.getX()) / 3;
    int centroidY = (int) (p1.getY() + p2.getY() + p3.getY()) / 3;
    super.setCenter(centroidX, centroidY);
  }

  @Override
  public void draw(Graphics2D g) {
    Color oldColor = g.getColor();
    g.setColor(getColor());
    g.fillPolygon(triangle);
    g.setColor(oldColor);
  }

  @Override
  public boolean clicked(int mouseX, int mouseY) {
    return triangle.contains(mouseX, mouseY);
  }

  @Override
  public void move(int Xpos, int Ypos) {
    triangle.translate(Xpos - (int) getCenter().getX(),
                       Ypos - (int) getCenter().getY());
    p1.translate(Xpos - (int) getCenter().getX(),
                 Ypos - (int) getCenter().getY());
    p2.translate(Xpos - (int) getCenter().getX(),
                 Ypos - (int) getCenter().getY());
    p3.translate(Xpos - (int) getCenter().getX(),
                 Ypos - (int) getCenter().getY());
    setCenter();
  }

  @Override
  public void resize(int Xpos, int Ypos) {
    int distanceP1 = (int) p1.distance(Xpos, Ypos);
    int distanceP2 = (int) p2.distance(Xpos, Ypos);
    int distanceP3 = (int) p3.distance(Xpos, Ypos);
    int minDistance = Math.min(Math.min(distanceP1, distanceP2),
                               distanceP3);
    if (minDistance == distanceP1) {
      p1.move(Xpos, Ypos);
    } else {
      if (minDistance == distanceP2) {
        p2.move(Xpos, Ypos);
      } else {
        p3.move(Xpos, Ypos);
      }
    }
    createPolygon();
    setCenter();
  }
}
