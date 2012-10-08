
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Mar 20, 2012 at 8:30:18 PM
 */
public class expressionTest {

  public static void main(String[] args) {
    String testStr1 = "( 6 + 2 )  * 5 - 8 / 4";
    System.out.println(testStr1);
    
    testStr1 = testStr1.replaceAll("\\s\\s", " ");
    System.out.println(testStr1);
    
    String testStr2 = "2093840328403.0238403284302.23843280";
    System.out.println(testStr2.matches(".*\\..*\\..*"));
    
    String testStr3 = "sdfaf--adfaf";
    System.out.println(testStr3);
    testStr3 = testStr3.replaceAll("--", "");
    System.out.println(testStr3);
    
    System.out.println();
    
    String testStr4 = " - 4 / - 5 + - 6 - 4";
    Pattern pattern = Pattern.compile("[\\+\\*\\/\\-]\\s\\-\\s");
    Matcher negateNum = pattern.matcher(testStr4);
    System.out.println(testStr4);
    boolean foundNeg = false;
    foundNeg = negateNum.find();
    while (foundNeg) {
      testStr4 = testStr4.replace(negateNum.group(), negateNum.group().substring(0,negateNum.group().length()-1));
      foundNeg = negateNum.find();
    }
    System.out.println(testStr4);
    
    System.out.println();
    
  }
}
