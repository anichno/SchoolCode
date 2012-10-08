
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.encog.ml.data.MLData;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.neural.networks.BasicNetwork;
import org.encog.persist.EncogDirectoryPersistence;
import org.encog.util.obj.SerializeObject;


/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Nov 8, 2011 at 4:53:54 PM
 */
public class TestNeuralNet {
  public final static int BOARD_WIDTH = 7;
  public final static int BOARD_HEIGHT = 6;
  public final static int TEST_ROUNDS = 1000;

  public static void main(String[] args) throws IOException, ClassNotFoundException {
    //BasicNetwork network = (BasicNetwork) SerializeObject.load(new File("brain/red.net"));
    BasicNetwork network = ( BasicNetwork ) EncogDirectoryPersistence.loadObject(new File("neuralnet/brain_train.eg"));
    Connect4 game = null;
    int yellowWins = 0;
    int redWins = 0;
    int whoWon = 0;
    int numCorrect = 0;
    for (int round = 0; round < TEST_ROUNDS; round++) {
      game = new Connect4(6, 7, 1, 1);

      int randMove = 0;
      while (!game.redWon() && !game.yellowWon() && !game.isTie()) {
        randMove = (int) (Math.random() * 7) + 1;
        game.nextMove(randMove);
        double temp = network.compute(board2data(board2string(game))).getData(0);
        //System.out.println(temp);
        if (temp > 0) {
          //System.out.println("hi");;
        }
        //game.nextMove(makeMove(game, network));
      }
      //System.out.println(game);
      //System.out.println(network.compute(board2data(board2string(game))));

      if (game.redWon()) {
        //System.out.println("Red");
        whoWon = 1;
        redWins++;
      } else if (game.yellowWon()) {
        //System.out.println("Yellow");
        //yellowWins++;
        whoWon = 2;
      }
      else {
        whoWon = 3;
      }
      double temp = network.compute(board2data(board2string(game))).getData(0);
      //System.out.println(temp);
//      if (game.redWon()) {
//        System.out.println(temp);
//      }
      if (temp > 0) {
        temp = 1;
      }
      //temp = Math.round(temp);
      if (whoWon == temp) {
        numCorrect++;
      }
    }
//    System.out.println(game);
//    System.out.println(network.winner(board2data(board2string(game))));
//    System.out.println(whoWon);
    System.out.println(numCorrect);
    System.out.println(redWins);
    System.out.println("Correct identification rate: " + (double)numCorrect / redWins);
    //System.out.println((double) yellowWins / TEST_ROUNDS);
  }
  
  public static String board2string(Connect4 game) {
    String tempGame = "";
    for (int index = 0; index < 5; index++) {
      int[] board = game.getRawBoard(index);
      for (int j = 0; j < board.length; j++) {
        if (board[j] == 1) {
              tempGame += board[j] + " ";
            }
            else {
              tempGame += 0 + " ";
            }
      }
    }
    return tempGame;
  }
  
  public static MLData board2data(String gameBoard) {
    MLData result = new BasicMLData(BOARD_WIDTH * BOARD_HEIGHT);
    Scanner board = new Scanner(gameBoard);
    for (int i = 0; board.hasNext(); i++) {
      result.setData(i, board.nextInt() - 1);
    }
    //System.out.println(gameBoard + "\r\n" + result);
    return result;
  }
}
