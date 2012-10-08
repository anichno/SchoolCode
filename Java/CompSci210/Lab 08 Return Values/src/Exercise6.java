
/**
 * Uses a method to return which absolute value of the numbers is larger
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 25, 2011 at 10:15:57 AM
 */
public class Exercise6 {

  public static void main(String[] args) {
    System.out.println("Which absolute value is larger? 11 or 2? " + largerAbsVal(11, 2));
    System.out.println("Which absolute value is larger? 4 or -5? " + largerAbsVal(4, -5));
    System.out.println("Which absolute value is larger? 7 or -11? " + largerAbsVal(7, -11));
  }

  public static int largerAbsVal(int num1, int num2) {
    int abs1 = Math.abs(num1);
    int abs2 = Math.abs(num2);

    if (abs1 >= abs2) {
      return num1;
    } else {
      return num2;
    }
  }
}
