
/**
 * Final Exam: Problem 4     Limit your time to approximately 18 minutes
 *
 * Grade: _____ / 20
 */
public class Prob04 {

  public static final int BOARD_SIZE = 8;
  public static final char EMPTY = '-';
  public static final char GREEN = 'G';
  public static final char BLUE = 'B';

  public static void main(String[] args) {
    char[][] gameBoard = new char[BOARD_SIZE][BOARD_SIZE];

    initBoard(gameBoard);
    printBoard(gameBoard);
  }

  public static void initBoard(char[][] gameBoard) {
    loadGreen(gameBoard);
    loadBlue(gameBoard);
    fillEmpty(gameBoard);
  }

  public static void loadGreen(char[][] gameBoard) {
    int curPos = gameBoard.length / 2;
    int curSpacing = 0;

    for (int i = 0; i < gameBoard.length; i++) {
      for (int j = 0; j < gameBoard[0].length - curSpacing; j++) {
        if (j == curPos) {
          if (gameBoard.length % 2 == 0 && j > 0) {
            //  Fill for even sized board
            gameBoard[i][j - 1] = GREEN;
          } else {
            //  Fill for odd sized board
            gameBoard[i][j] = GREEN;
          }
          gameBoard[i][j + curSpacing] = GREEN;
          curPos--;
          curSpacing += 2;
        }
      }
    }
  }

  public static void loadBlue(char[][] gameBoard) {
    for (int i = 0; i < gameBoard[0].length; i++) {
      gameBoard[gameBoard.length - 1][i] = BLUE;
    }
  }

  public static void fillEmpty(char[][] gameBoard) {
    for (int i = 0; i < gameBoard.length; i++) {
      for (int j = 0; j < gameBoard[0].length; j++) {
        if (gameBoard[i][j] != GREEN && gameBoard[i][j] != BLUE) {
          gameBoard[i][j] = '-';
        }
      }
    }
  }

  public static void printBoard(char[][] gameBoard) {
    for (int i = 0; i < gameBoard.length; i++) {
      for (int j = 0; j < gameBoard[0].length; j++) {
        System.out.print(gameBoard[i][j] + "  ");
      }
      System.out.println();
    }
  }
}
