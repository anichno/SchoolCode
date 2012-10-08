
import java.awt.Color;

public class Square extends Rectangle {

  public Square(int x, int y, int height, Color color) {
    super(x, y, height, height, color);
  }

  @Override
  public void resize(int Xpos, int Ypos) {
    if (Xpos < getCenter().getX() && Ypos < getCenter().getY()) {
      int origX = x;
      int origY = y;
      x = Xpos;
      y = Ypos;
      if (Xpos > Ypos) {
        height += origY - y;
        width += origY - y;
      } else {
        width += origX - x;
        height += origX - x;
      }
    } else if (Xpos > getCenter().getX() && Ypos > getCenter().getY()) {
      int mousePos = Math.max(Xpos, Ypos);
      width = mousePos - x;
      height = width;
    } else if (Xpos > getCenter().getX() && Ypos < getCenter().getY()) {
      int origY = y;
      y = Ypos;
      height += origY - y;
      width = height;
    } else if (Xpos < getCenter().getX() && Ypos > getCenter().getY()) {
      int origX = x;
      x = Xpos;
      width += origX - x;
      height = width;
    }
    setCenter(x + (width / 2), y + (height / 2));
  }
}
