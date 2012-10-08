
/**
 * Returns whether a string starts and ends with the same letter
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 19, 2011 at 3:47:21 PM
 */
public class Starts_and_end_the_same {

  public static void main(String[] args) {
    // tests
    String test = "this is a test";
    System.out.printf("Does '%s' start and end with the same letter? %b\n", test, startEndSame(test));

    test = "Ice cream";
    System.out.printf("Does '%s' start and end with the same letter? %b\n", test, startEndSame(test));

    test = "USAFA";
    System.out.printf("Does '%s' start and end with the same letter? %b\n", test, startEndSame(test));

  }

  public static boolean startEndSame(String str) {
    return str.charAt(0) == str.charAt(str.length() - 1);
  }
}
