
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public abstract class Shape {

  private Color color;
  private Point centerPoint;

  public Shape(Color color) {
    this.color = color;
  }

  public Color getColor() {
    return this.color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public Point getCenter() {
    return this.centerPoint;
  }

  public void setCenter(int x, int y) {
    centerPoint = new Point(x, y);
  }

  public abstract void draw(Graphics2D g);

  public abstract boolean clicked(int mouseX, int mouseY);

  public abstract void move(int Xpos, int Ypos);

  public abstract void resize(int Xpos, int Ypos);
}
