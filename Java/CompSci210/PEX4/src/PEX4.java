
/**
 * An implementation of a control class for a connect 4 game
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Nov 20, 2011 at 5:14:45 PM
 *
 * Documentation Statement: I have received no help on this assignment.
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.swing.JOptionPane;

public class PEX4 {

  private static final int SCREEN_SIZE = 600;
  private static final int TWOP_LOCAL = 1;
  private static final int TWOP_REMOTE = 2;
  private static final int ONEP_COMP = 3;
  private static final int NOBODY = 0;
  private static final int RED_PLAYER = 1;
  private static final int YELLOW_PLAYER = 2;
  static Connect4 game = null;

  public static void main(String[] args) {
    setupGame();
    playGame();
  }

  public static void setupGame() {
    String strRows;
    String strColumns;
    String strGameMode;
    String strFirstPlayer;
    int rows = 0;
    int columns = 0;
    int gameMode = 0;
    int firstPlayer = 0;
    String redPlayer = null;
    String yellowPlayer = null;

    while (gameMode < 1 || gameMode > 3) {
      strGameMode = JOptionPane.showInputDialog("Select Game Mode:\r\n1: Two Player Local\r\n2: Two Player Remote\r\n3: One Player versus Computer");
      if (strGameMode == null) {
        System.exit(0);
      }
      try {
        gameMode = Integer.parseInt(strGameMode);
      } catch (NumberFormatException error) {
      }
    }

    strRows = JOptionPane.showInputDialog("How many rows on the board?");
    if (strRows == null) {
      System.exit(0);
    }
    rows = Integer.parseInt(strRows);

    strColumns = JOptionPane.showInputDialog("How many columns on the board");
    if (strColumns == null) {
      System.exit(0);
    }
    columns = Integer.parseInt(strColumns);

    redPlayer = JOptionPane.showInputDialog("What is the red player's name?");
    if (redPlayer == null) {
      System.exit(0);
    }
    if (gameMode == TWOP_LOCAL) {
      yellowPlayer = JOptionPane.showInputDialog("What is the yellow player's name?");
      if (yellowPlayer == null) {
        System.exit(0);
      }
      while (firstPlayer < 1 || firstPlayer > 2) {
        strFirstPlayer = JOptionPane.showInputDialog(String.format("Select first player:\r\n1: %s\r\n2: %s", redPlayer, yellowPlayer));
        if (strFirstPlayer == null) {
          System.exit(0);
        }
        firstPlayer = Integer.parseInt(strFirstPlayer);
      }
    } else {
      if (gameMode == TWOP_REMOTE) {
        yellowPlayer = "Remote";
      } else {
        yellowPlayer = "Computer";
      }
    }

    game = new Connect4(rows, columns, gameMode, firstPlayer);
    game.setRedName(redPlayer);
    game.setYellowName(yellowPlayer);
  }

  public static void playGame() {
    DrawingPanel panel = new DrawingPanel(SCREEN_SIZE, SCREEN_SIZE);
    panel.setBackground(Color.blue);
    Graphics2D g = (Graphics2D) panel.getGraphics();
    game.drawGrid(g);
    Font newFont = new Font("Courier New", Font.BOLD, 40);
    g.setFont(newFont);
    g.setColor(Color.red);
    g.drawString("Turn: " + game.whoseTurn(), 20, 40);
    panel.copyGraphicsToScreen();

    while (game.winner() == NOBODY && !game.isTie()) {
      g.setColor(Color.blue);
      g.fillRect(0, 0, SCREEN_SIZE, 60);
      g.setColor(Color.red);
      if (game.whoseTurn().equals(game.getRedName()) || game.getGameMode() == RED_PLAYER) {
        game.nextMove(getMove(panel));
      } else {
        game.nextMove();
      }
      game.drawMove(g);
      g.drawString("Turn: " + game.whoseTurn(), 20, 40);
      panel.copyGraphicsToScreen();
    }
    g.setColor(Color.blue);
    g.fillRect(0, 0, SCREEN_SIZE, 60);
    g.setColor(Color.red);

    if (game.winner() == RED_PLAYER) {
      g.drawString("Winner: " + game.getRedName(), 20, 40);
    } else {
      if (game.winner() == YELLOW_PLAYER) {
        g.drawString("Winner: " + game.getYellowName(), 20, 40);
      } else {
        g.drawString("Tie Game", 20, 40);
        System.out.println("Tie game.");
      }
    }
    panel.copyGraphicsToScreen();
  }

  public static int getMove(DrawingPanel panel) {
    int xClickLocation = 0;
    while (true) {
      if (panel.isMouseButtonDown(DrawingPanel.LEFT_BUTTON)) {
        while (panel.isMouseButtonDown(DrawingPanel.LEFT_BUTTON)) {
          panel.sleep(50);
        }
        xClickLocation = panel.getMouseX();
        for (int columns = 0, colSpacer = (SCREEN_SIZE / game.getColumns()) / 2; columns < game.getColumns(); columns++, colSpacer += SCREEN_SIZE / (game.getColumns() + 1)) {
          if (xClickLocation > colSpacer && xClickLocation < colSpacer + SCREEN_SIZE / (game.getColumns() + 1)) {
            return columns + 1;
          }
        }
      }
    }
  }
}
