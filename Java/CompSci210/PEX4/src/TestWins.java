
/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Nov 9, 2011 at 2:57:31 PM
 */
public class TestWins {

  public static void main(String[] args) {
    final int TEST_ROUNDS = 10;
    final int BOARD_ROWS = 6;
    final int BOARD_COLUMNS = 7;
    
    Connect4 game = null;
    for (int round = 0; round < TEST_ROUNDS; round++) {
      game = new Connect4(BOARD_ROWS, BOARD_COLUMNS, 1, 1);

      int randMove = 0;
      int lastmove = 0;
      while (!game.redWon() && !game.yellowWon() && !game.isTie()) {
        randMove = (int) (Math.random() * BOARD_COLUMNS) + 1;
        game.nextMove(randMove);
        lastmove = randMove;
      }
      System.out.println("--------------------------");
      System.out.println(game);
      System.out.println("Last Move: " + lastmove);

      if (game.redWon()) {
      System.out.println("Red Won!");
    } else {
      if (game.yellowWon()) {
        System.out.println("Yellow Won!");
      } else {
        System.out.println("Tie game.");
      }
    }
    }
  }
}
