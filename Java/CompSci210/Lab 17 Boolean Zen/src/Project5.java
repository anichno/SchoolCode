
/**
 * Plays rock paper scissors
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 21, 2011 at 10:19:20 AM
 */
import java.util.Scanner;

public class Project5 {

  public static void main(String[] args) {
    Scanner user = new Scanner(System.in);
    int userSelect = 0;
    for (boolean quit = false; quit != true;) {
      System.out.println("Rock Paper Scissors! Choose your move:");
      System.out.println("Rock\t\t[1]");
      System.out.println("Paper\t\t[2]");
      System.out.println("Scissors\t[3]");
      System.out.println("Quit\t\t[4]");

      if (user.hasNextInt()) {
        userSelect = user.nextInt();
        if (userSelect > 0 && userSelect < 5) {
          if (userSelect == 4) {
            quit = true;
          } else {
            whoWon(userSelect);
          }
        } else {
          error();
        }
      } else {
        error();
      }
    }
  }

  public static void whoWon(int userSelect) {
    String[] choices = new String[]{"Rock", "Paper", "Scissors"};
    int computerChoice = (int) (Math.random() * 3);
    userSelect--;
    System.out.println("User played: " + choices[userSelect] + "\nComputer played: " + choices[computerChoice]);

    if (userSelect > computerChoice && (userSelect != 2 || computerChoice != 0) || (userSelect == 0 && computerChoice == 2)) {
      System.out.println("User Wins\n");
    } else if (userSelect == computerChoice) {
      System.out.println("Tie\n");
    } else {
      System.out.println("User Loses\n");
    }
  }

  public static void error() {
    System.out.println("Please Enter a number between 1 and 4\n");
  }
}
