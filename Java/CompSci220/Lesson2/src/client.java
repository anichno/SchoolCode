
/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Jan 6, 2012 at 9:28:24 AM
 */
public class client {

  public static void main(String[] args) {
    Rectangle rect1 = new Rectangle();
    Square square1 = new Square();
    Rectangle rect2 = new Rectangle(10, 15);
    Square square2 = new Square(10);
    
    System.out.println(rect1.getArea());
    System.out.println(square1.getArea());
    System.out.println(rect2.getArea());
    System.out.println(square2.getArea());
  }
}
