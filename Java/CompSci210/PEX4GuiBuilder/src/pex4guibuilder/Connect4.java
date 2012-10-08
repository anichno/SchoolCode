
/**
 * An object which describes a connect 4 game board and game state
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Nov 6, 2011 at 8:25:40 PM
 *
 * Documentation Statement: I have received no help on this assignment.
 */
import java.awt.Color;
import java.awt.Graphics2D;

public class Connect4 {

  private final int SCREEN_SIZE = 600;
  private final int EMPTY_SLOT = 0;
  private final int RED_PLAYER = 1;
  private final int YELLOW_PLAYER = 2;
  private int[][] board = null;
  private boolean redTurn = true;
  private int opponent = 0;
  private int rows = 0;
  private int columns = 0;
  private int[] lastMove = new int[]{0, 0};
  private String redName = "Red";
  private String yellowName = "Yellow";

  public Connect4(int rows, int columns, int opponent, int firstPlayer) {
    final int SECOND_PLAYER = 2;

    this.opponent = opponent;
    this.rows = rows;
    this.columns = columns;
    this.board = new int[rows][columns];
    if (firstPlayer == SECOND_PLAYER) {
      redTurn = false;
    }
  }

  public void setRedName(String redName) {
    this.redName = redName;
  }

  public void setYellowName(String yellowName) {
    this.yellowName = yellowName;
  }

  public String getRedName() {
    return new String(redName);
  }

  public String getYellowName() {
    return new String(yellowName);
  }

  public void nextMove(int p) {
    p -= 1;
    for (int counter = board.length - 1; counter >= 0; counter--) {
      lastMove[0] = counter;
      lastMove[1] = p;
      if (board[counter][p] == EMPTY_SLOT) {
        if (redTurn) {
          board[counter][p] = RED_PLAYER;
        } else {
          board[counter][p] = YELLOW_PLAYER;
        }
        break;
      }
    }
    redTurn = !redTurn;
  }

  public void nextMove() {
    if (opponent == 2 && !redTurn && winner() != RED_PLAYER) {
      remoteMove();
    } else {
      if (opponent == 3 && !redTurn && winner() != RED_PLAYER) {
        compMove();
      }
    }
  }

  private void compMove() {
    int randMove = (int) (Math.random() * 7) + 1;
    nextMove(randMove);
  }

  public void remoteMove() {
    compMove();
  }

  public String toString() {
    String boardStr = "";

    for (int counter = 0; counter < board.length; counter++) {
      boardStr += "|";
      for (int counter2 = 0; counter2 < board[0].length; counter2++) {
        if (board[counter][counter2] == RED_PLAYER) {
          boardStr += "R|";
        } else {
          if (board[counter][counter2] == RED_PLAYER) {
            boardStr += "Y|";
          } else {
            boardStr += " |";
          }
        }
      }
      boardStr += "\n";
    }
    return new String(boardStr);
  }

  public int winner() {
    for (int check = 1; check <= 2; check++) {
      //  Check horizontal
      for (int row = 0; row < board.length; row++) {
        for (int column = 0; column < board[0].length - 3; column++) {
          for (int counter = 0; counter < 5; counter++) {
            if (counter == 4) {
              return check;
            }
            if (board[row][column + counter] == check) {
              continue;
            } else {
              break;
            }
          }
        }
      }

      //  Check vertical
      for (int row = board.length - 4; row >= 0; row--) {
        for (int column = 0; column < board[0].length; column++) {
          for (int counter = 0; counter < 5; counter++) {
            if (counter == 4) {
              return check;
            }
            if (board[row + counter][column] == check) {
              continue;
            } else {
              break;
            }
          }
        }
      }

      //  Check diag left-right
      for (int row = board.length - 4; row >= 0; row--) {
        for (int column = 0; column < board[0].length - 3; column++) {
          for (int counter = 0; counter < board.length; counter++) {
            if (counter == 4) {
              return check;
            }
            if (board[row + counter][column + counter] == check) {
              continue;
            } else {
              break;
            }
          }
        }
      }

      //  Check diag right-left
      for (int row = 0; row < board.length - 4; row++) {
        for (int counter = 0; counter < board[0].length - 3; counter++) {
          if (counter == 4) {
            return check;
          }
          if (board[row + counter][6 - counter] == check) {
            continue;
          } else {
            break;
          }
        }
      }

    }
    return 0;
  }

  public boolean isTie() {
    for (int counter = 0; counter < board.length; counter++) {
      for (int counter2 = 0; counter2 < board[0].length; counter2++) {
        if (board[counter][counter2] == EMPTY_SLOT) {
          return false;
        }
      }
    }
    return true;
  }

  public String whoseTurn() {
    if (redTurn) {
      return getRedName();
    }
    return getYellowName();
  }

  public boolean RedisAt(int row, int column) {
    if (board[row][column] == RED_PLAYER) {
      return true;
    }
    return false;
  }

  public boolean YellowisAt(int row, int column) {
    if (board[row][column] == YELLOW_PLAYER) {
      return true;
    }
    return false;
  }

  public int getColumns() {
    return columns;
  }

  public int getRows() {
    return rows;
  }

  public int getGameMode() {
    return opponent;
  }

  public int[] getLastMove() {
    return lastMove;
  }

  public void drawGrid(Graphics2D g) {
    Color oldColor = g.getColor();
    g.setColor(Color.white);
    for (int rows = 0, rowSpacer = SCREEN_SIZE / (getRows()); rows < getRows(); rows++, rowSpacer += SCREEN_SIZE / (getColumns() + 1)) {
      for (int columns = 0, colSpacer = (SCREEN_SIZE / getColumns()) / 2; columns < getColumns(); columns++, colSpacer += SCREEN_SIZE / (getColumns() + 1)) {
        g.fillOval(colSpacer, rowSpacer, SCREEN_SIZE / (getColumns() + 1), SCREEN_SIZE / (getColumns() + 1));
      }
    }
    g.setColor(oldColor);
  }

  public void drawMove(Graphics2D g) {
    int[] lastMove = getLastMove();
    int xPos = lastMove[1] * (SCREEN_SIZE / (getColumns() + 1)) + (SCREEN_SIZE / getColumns()) / 2;
    int yPos = lastMove[0] * (SCREEN_SIZE / (getColumns() + 1)) + SCREEN_SIZE / getRows();
    Color oldColor = g.getColor();

    if (whoseTurn().equals("Red") && RedisAt(lastMove[0], lastMove[1])) {
      g.setColor(Color.red);
    } else {
      if (YellowisAt(lastMove[0], lastMove[1])) {
        g.setColor(Color.yellow);
      }
    }

    g.fillOval(xPos, yPos, SCREEN_SIZE / (getColumns() + 1), SCREEN_SIZE / (getColumns() + 1));
    g.setColor(oldColor);
  }
}
