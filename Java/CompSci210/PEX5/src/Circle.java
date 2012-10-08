
import java.awt.Color;

public class Circle extends Ellipse {

  public Circle(int x, int y, int radius, Color color) {
    super(x, y, radius, radius, color);
  }

  @Override
  public void resize(int Xpos, int Ypos) {
    int mouseRadius = (int) Math.sqrt((Xpos - getCenter().getX())
                                      * (Xpos - getCenter().getX())
                                      + (Ypos - getCenter().getY())
                                        * (Ypos - getCenter().getY()));

    xRadius = mouseRadius;
    yRadius = mouseRadius;
    setCenter(x + (xRadius / 2), y + (yRadius / 2));
  }
}
