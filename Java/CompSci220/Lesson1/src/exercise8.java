
/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Jan 4, 2012 at 9:19:01 AM
 */
import java.util.Scanner;

public class exercise8 {

  public static void main(String[] args) {
    Scanner user = new Scanner(System.in);
    System.out.print("Please enter a string: ");
    String userInput = user.nextLine();
    System.out.print("Please enter the substring: ");
    String subString = user.nextLine();
    System.out.print("Please enter the replacement: ");
    String replacement = user.nextLine();
    
    Scanner parser = new Scanner(userInput);
    String output = "";
    while(parser.hasNext()) {
      String temp = parser.next();
      if (temp.equals(subString)) {
        output += replacement;
      }
      else {
        output += temp;
      }
      output += " ";
    }
    System.out.print("Output String: ");
    System.out.println(output);
  }
}
