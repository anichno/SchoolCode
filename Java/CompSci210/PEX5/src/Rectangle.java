
import java.awt.Color;
import java.awt.Graphics2D;

public class Rectangle extends Shape {

  protected int x;
  protected int y;
  protected int width;
  protected int height;

  public Rectangle(int x, int y, int width, int height, Color color) {
    super(color);
    this.x = x - (width / 2);
    this.y = y - (height / 2);
    this.width = width;
    this.height = height;
    setCenter(x, y);
  }

  @Override
  public void draw(Graphics2D g) {
    Color oldColor = g.getColor();
    g.setColor(getColor());
    g.fillRect(this.x, this.y, this.width, this.height);
    g.setColor(oldColor);
  }

  @Override
  public boolean clicked(int mouseX, int mouseY) {
    if (mouseX < getCenter().getX() + this.width / 2
        && mouseX > getCenter().getX() - this.width / 2
        && mouseY < getCenter().getY() + this.height / 2
        && mouseY > getCenter().getY() - this.height / 2) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void move(int Xpos, int Ypos) {
    this.x = Xpos - (width / 2);
    this.y = Ypos - (height / 2);
    setCenter(Xpos, Ypos);
  }

  @Override
  public void resize(int Xpos, int Ypos) {
    if (Xpos < getCenter().getX() && Ypos < getCenter().getY()) {
      int origX = x;
      int origY = y;
      x = Xpos;
      y = Ypos;
      width += origX - x;
      height += origY - y;
    } else {
      if (Xpos > getCenter().getX() && Ypos > getCenter().getY()) {
        width = Xpos - x;
        height = Ypos - y;
      } else {
        if (Xpos > getCenter().getX() && Ypos < getCenter().getY()) {
          int origY = y;
          y = Ypos;
          height += origY - y;
          width = Xpos - x;
        } else {
          if (Xpos < getCenter().getX() && Ypos > getCenter().getY()) {
            int origX = x;
            x = Xpos;
            width += origX - x;
            height = Ypos - y;
          }
        }
      }
    }

    setCenter(x + (width / 2), y + (height / 2));
  }
}
