
/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Jan 19, 2012 at 10:02:31 AM
 */
public class adderTester {

  public static void main(String[] args) {
    for (int i = 0; i < 16; i++) {
      int num1 = (int) (Math.random()*8);
      int num2 = (int) (Math.random()*8);
      int sum = num1+num2;
      
      System.out.println(Integer.toBinaryString(num1) + " + " + Integer.toBinaryString(num2) + " = " + Integer.toBinaryString(sum));
    }
  }
}
