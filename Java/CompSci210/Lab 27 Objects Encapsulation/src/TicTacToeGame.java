/**
 * Lab 27 - Tic-Tac-Toe Game Client code
 * 
 * @author David.Gibson
 * 
 * @version 1.0 - Oct 21, 2011 at 8:37:55 PM
 */
import java.util.Scanner;

public class TicTacToeGame {

  public static boolean COMPUTER_MOVES_FIRST = true;
  
  public static void main(String[] args) {

    TicTacToe game = new TicTacToe();
    
    System.out.println(game.toString());
    while (!game.xWon() && !game.oWon() && !game.isTie()) {
      game.nextMove(getMove());
      System.out.println(game.toString());
    }
    
    if (game.xWon()) {
      System.out.println("X Won!");
    } else if (game.oWon()) {
      System.out.println("O Won!");
    } else {
      System.out.println("Tie game.");
    }
  }

  public static int getMove() {
    Scanner input = new Scanner(System.in);
    int move = 0;
    while (move < 1 || move > 9) {
      System.out.print("Enter move (1-9): ");
      if (input.hasNextInt()) {
        move = input.nextInt();
      } else {
        input.next();  // discard bad input
        move = 0;
      }
    }
    return move;
  }
}
