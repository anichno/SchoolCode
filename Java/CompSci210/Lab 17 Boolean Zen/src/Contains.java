
/**
 * Returns whether a string contains a specific character
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 19, 2011 at 3:25:16 PM
 */
public class Contains {

  public static void main(String[] args) {

    String test = "Now is the time for all good men to come to the aid of their country";

    // test the method contains
    for (char c = 'a'; c <= 'z'; c += 1) {
      System.out.printf("Does the string contain %c?  %b\n", c, contains(test, c));
    }
  }

  //-------------------------------------------------------------------
  public static boolean contains(String str, char ch) {
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == ch) {
        return true;
      }
    }
    return false;
  }
}
