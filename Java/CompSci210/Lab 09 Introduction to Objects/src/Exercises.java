
/**
 * A big program that has methods for exercises 11, 12, 13, 14, and 15
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 29, 2011 at 9:56:15 AM
 */
import java.util.Scanner;

public class Exercises {

  public static void main(String[] args) {
    Scanner user = new Scanner(System.in);
//    System.out.print(padString("hello", 8));
//    System.out.println(padString("hello", 8));
//    vertical("Hello World");
//    printReverse("Hello World");
//    inputBirthday(user);
    processName(user);
  }

  public static String padString(String input, int pad) {
    String output = input;

    if (output.length() == pad) {
      return output;
    }
    for (int counter = output.length(); counter <= pad; counter++) {
      output = output.concat(" ");
    }
    return output;
  }

  public static void vertical(String input) {
    for (int counter = 0; counter < input.length(); counter++) {
      System.out.println(input.charAt(counter));
    }
  }

  public static void printReverse(String input) {
    for (int counter = input.length() - 1; counter >= 0; counter--) {
      System.out.print(input.charAt(counter));
    }
    System.out.println();
  }

  public static void inputBirthday(Scanner user) {
    System.out.print("On what day of the month were you born? ");
    int day = user.nextInt();
    System.out.print("What is the name of the month in which you were born? ");
    String month = user.next();
    System.out.print("During what year were you born? ");
    int year = user.nextInt();
    System.out.println("You were born on " + month + " " + day + ", " + year + ".");
  }

  public static void processName(Scanner name) {
    System.out.print("Please enter your full name: ");
    String inputName = name.nextLine();
    String[] splitName = inputName.split("\\s");
    System.out.println("Your name in reverse order is " + splitName[1] + ", " + splitName[0]);
  }
}
