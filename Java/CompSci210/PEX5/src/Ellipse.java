
import java.awt.Color;
import java.awt.Graphics2D;

public class Ellipse extends Shape {

  protected int x;
  protected int y;
  protected int xRadius;
  protected int yRadius;

  public Ellipse(int x, int y, int xRadius, int yRadius, Color color) {
    super(color);
    this.x = x - (xRadius / 2);
    this.y = y - (yRadius / 2);
    this.xRadius = xRadius;
    this.yRadius = yRadius;
    setCenter(x, y);
  }

  @Override
  public void draw(Graphics2D g) {
    Color oldColor = g.getColor();
    g.setColor(getColor());
    g.fillOval(this.x, this.y, this.xRadius, this.yRadius);
    g.setColor(oldColor);
  }

  @Override
  public boolean clicked(int mouseX, int mouseY) {
    if (mouseX < getCenter().getX() + this.xRadius / 2
        && mouseX > getCenter().getX() - this.xRadius / 2
        && mouseY < getCenter().getY() + this.yRadius / 2
        && mouseY > getCenter().getY() - this.yRadius / 2) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void move(int Xpos, int Ypos) {
    this.x = Xpos - (xRadius / 2);
    this.y = Ypos - (yRadius / 2);
    setCenter(Xpos, Ypos);
  }

  @Override
  public void resize(int Xpos, int Ypos) {
    if (Xpos < getCenter().getX() && Ypos < getCenter().getY()) {
      int origX = x;
      int origY = y;
      x = Xpos;
      y = Ypos;
      xRadius += origX - x;
      yRadius += origY - y;
    } else {
      if (Xpos > getCenter().getX() && Ypos > getCenter().getY()) {
        xRadius = Xpos - x;
        yRadius = Ypos - y;
      } else {
        if (Xpos > getCenter().getX() && Ypos < getCenter().getY()) {
          int origY = y;
          y = Ypos;
          xRadius = Xpos - x;
          yRadius += origY - y;
        } else {
          if (Xpos < getCenter().getX() && Ypos > getCenter().getY()) {
            int origX = x;
            x = Xpos;
            xRadius += origX - x;
            yRadius = Ypos - y;
          }
        }
      }
    }

    setCenter(x + (xRadius / 2), y + (yRadius / 2));
  }
}
