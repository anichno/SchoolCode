
/**
 * Prints the output of a physics formula
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 11, 2011 at 10:40:08 AM
 */
public class Exercise_1 {

  public static void main(String[] args) {
    double s = 0;
    double s0 = 5;
    double v0 = 5;
    double a = 9;
    double t = 3;

    s = s0 + (v0 * t) + (.5 * a * (t * t));
    System.out.println(s);
  }
}
