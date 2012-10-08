
/**
 * Prints the number at a specified index
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 11, 2011 at 10:46:40 AM
 */
public class PrintNum {

  public static void main(String[] args) {
    int num = 12345;
    int whichtoprint = 3;   //index position of number

    String number = Integer.toString(num);
    char[] numarray = number.toCharArray();

    System.out.println("Your number is: " + num);
    System.out.println("The position you want to print is: " + whichtoprint);
    System.out.println("The number at that position is: " + numarray[whichtoprint]);
  }
}
