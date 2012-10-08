
/**
 * Uses a method to return which absolute value of the numbers is larger
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 25, 2011 at 10:22:26 AM
 */
public class Exercise7 {

  public static void main(String[] args) {
    System.out.println("Which absolute value is larger? 7, -2, or -11? " + largerAbsVal(7, -2, -11));
    System.out.println("Which absolute value is larger? -4, 5, or 2? " + largerAbsVal(-4, 5, 2));
    System.out.println("Which class year is better? 12, 13, or 14? " + largerAbsVal(12, 13, 14));
  }

  public static int largerAbsVal(int num1, int num2, int num3) {
    int abs1 = Math.abs(num1);
    int abs2 = Math.abs(num2);
    int abs3 = Math.abs(num3);

    if (abs1 >= abs2 && abs1 >= abs3) {
      return num1;
    } else {
      if (abs2 >= abs1 && abs2 >= abs3) {
        return num2;
      } else {
        return num3;
      }
    }
  }
}
