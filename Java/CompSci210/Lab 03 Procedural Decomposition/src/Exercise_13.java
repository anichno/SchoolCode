
/**
 * Prints the jack statement using recursion
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 10, 2011 at 12:19:26 PM
 */
public class Exercise_13 {

  public static void main(String[] args) {
    int counter = 1;
    jack(counter);
  }
  
  public static void jack(int counter) {
    System.out.println("All work and no play makes Jack a dull boy.\t Statement #: " + counter);
    if (counter < 1000) {
      counter++;
      jack(counter);
    }
  }
}
