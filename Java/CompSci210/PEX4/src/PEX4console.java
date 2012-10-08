
/**
 * <put a program description here>
 * 
 * @author anichno
 * 
 * @version 1.0 - Nov 6, 2011 at 8:20:52 PM
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class PEX4console {

  public static void main(String[] args) {
    int gameMode = 0;
    int firstPlayer = 0;
    Scanner input = new Scanner(System.in);

    //buildTrainingData(1, 1000);
    //game.testwin();
    //testRandom(10000);

    System.out.println("Select game mode:\r\n"
                       + "1. 2 Player Local\r\n"
                       + "2. 2 Player Remote\r\n"
                       + "3. 1 Player vs. Computer\r\n");
    while (gameMode < 1 || gameMode > 3) {
      System.out.print("Selection: ");
      if (input.hasNextInt()) {
        gameMode = input.nextInt();
      } else {
        input.next();  // discard bad input
      }
    }

    System.out.println("Select who has the first move:\r\n"
                       + "1. Red\r\n"
                       + "2. Yellow");

    while (firstPlayer < 1 || firstPlayer > 2) {
      System.out.print("Selection: ");
      if (input.hasNextInt()) {
        firstPlayer = input.nextInt();
      } else {
        input.next();  // discard bad input
      }
    }

    Connect4 game = new Connect4(6, 7, gameMode, firstPlayer);

    System.out.println(game.toString());
    while (!game.redWon() && !game.yellowWon() && !game.isTie()) {
      System.out.println("Turn: " + game.whoseTurn());
      if (game.whoseTurn().equals("Red") || gameMode == 1) {
        game.nextMove(getMove(input));
      } else {
        game.nextMove();
      }
      System.out.println(game.toString());
    }

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

  public static int getMove(Scanner input) {
    int move = 0;
    while (move < 1 || move > 7) {
      System.out.print("Enter move (1-7): ");
      if (input.hasNextInt()) {
        move = input.nextInt();
      } else {
        input.next();  // discard bad input
        move = 0;
      }
    }
    return move;
  }

  public static void testRandom(int numRounds) {
    int randMove = 0;
    int yellowWins = 0;
    Connect4 game = null;
    for (int i = 0; i < numRounds; i++) {
      game = new Connect4(6, 7, 0, 1);
      while (!game.redWon() && !game.yellowWon() && !game.isTie()) {
        randMove = (int) (Math.random() * 7) + 1;
        game.nextMove(randMove);
      }
//      System.out.println("----------------");
//      System.out.println(game.toString());
      if (game.redWon()) {
//        System.out.println("Red Won!");
      } else {
        if (game.yellowWon()) {
          yellowWins++;
//          System.out.println("Yellow Won!");
        } else {
//          System.out.println("Tie game.");
        }
      }
    }
    System.out.println((double) yellowWins / numRounds);
    System.exit(0);
  }

  public static void buildTrainingData(int whichWinner, int numWins) {
    try {
      PrintStream trainingDataFile = new PrintStream(new File("TrainingData.csv"));
      for (int i = 0; i < 6 * 7; i++) {
        trainingDataFile.print("\"b" + i + "\",");
      }
      trainingDataFile.println("\"result\"");

      int randMove = 0;
      int redWins = 0;
      int yellowWins = 0;
      int counter = 0;
      Connect4 game = null;
      String tempGame = null;
      while (redWins < numWins) {
        tempGame = "";
        game = new Connect4(6, 7, 3, 1);
        while (!game.redWon() && !game.yellowWon() && !game.isTie()) {
          tempGame = "";
          randMove = (int) (Math.random() * 7) + 1;
          game.nextMove(randMove);
          
          for (int index = 0; index < 6; index++) {
            int[] board = game.getRawBoard(index);
            for (int j = 0; j < board.length; j++) {
              if (board[j] == whichWinner) {
                tempGame += (double)board[j] + ", ";
              } else {
                tempGame += (double)0 + ", ";
              }
            }
          }
          counter++;
          
          if (!game.redWon() && !game.yellowWon() && !game.isTie() && counter%150 == 0) {
            trainingDataFile.print(tempGame);
            trainingDataFile.println(0);
          }
        }
//        for (int index = 0; index < 6; index++) {
//          int[] board = game.getRawBoard(index);
//          for (int j = 0; j < board.length; j++) {
//            if (board[j] == whichWinner) {
//              tempGame += (double)board[j] + ", ";
//            }
//            else {
//              tempGame += 0 + ", ";
//            }
//          }
//        }
        trainingDataFile.print(tempGame);
        if (game.redWon()) {
          //trainingDataFile.println(tempGame);
          if (whichWinner == 1) {
            trainingDataFile.println(1);
          } else {
            trainingDataFile.println(0);
          }
          redWins++;
        } else {
          if (game.yellowWon()) {
            //trainingDataFile.println(tempGame);
            if (whichWinner == 2) {
              trainingDataFile.println(2);
            } else {
              trainingDataFile.println(0);
            }
            yellowWins++;
          } else {
            trainingDataFile.println(0);
          }
        }
      }
    } catch (IOException error) {
      System.out.println("An I/O error occurred: " + error);
    }

    System.exit(0);
  }
}