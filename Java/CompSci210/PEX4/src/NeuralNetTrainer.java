
/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Nov 7, 2011 at 6:19:18 PM
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.encog.Encog;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.training.simple.TrainAdaline;
import org.encog.neural.pattern.ADALINEPattern;
import org.encog.util.obj.SerializeObject;

/**
 * This example demonstrates the ADALINE neural network.  The ADALINE network 
 * is a very simple network that is often used for pattern recognition.  The 
 * input pattern must match EXACTLY with what the network was trained with.
 * 
 * This example teaches the ADALINE to recognize the 10 digits.
 * 
 * This is based on a an example by Karsten Kutza, 
 * written in C on 1996-01-24.
 * http://www.neural-networks-at-your-fingertips.com
 */
public class NeuralNetTrainer {

  public final static int BOARD_WIDTH = 7;
  public final static int BOARD_HEIGHT = 6;
  public final static int TEST_ROUNDS = 1000;
  public final static int NUM_NEURONS = 2;

  public static MLDataSet generateTraining() {
    Scanner file = null;
    int winner = 0;
    MLDataSet result = new BasicMLDataSet();

    try {
      file = new Scanner(new File("TrainingData.txt"));
    } catch (FileNotFoundException ex) {
      Logger.getLogger(NeuralNetTrainer.class.getName()).log(Level.SEVERE, null, ex);
    }

    while (file.hasNextLine()) {
      BasicMLData ideal = new BasicMLData(NUM_NEURONS);
      MLData input = board2data(file.nextLine());
      winner = Integer.parseInt(file.nextLine());
      for (int whichWinner = 0; whichWinner < NUM_NEURONS; whichWinner++) {
        if (1 == winner) {
          ideal.setData(whichWinner, 1);
        } else {
          ideal.setData(whichWinner, -1);
        }
      }
      result.add(input, ideal);
    }

    return result;
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

  public static int makeMove(Connect4 game, BasicNetwork network) {
    return network.winner(board2data(board2string(game))) + 1;
//    int[] mode = new int[7];
//    int max = 0;
//    
//    for (int i = 0; i < 10; i++) {
//      mode[network[i].winner(board2data(board2string(game)))]++;
//    }
//    for (int i = 0; i < 7; i++) {
//      if (mode[i] > max) {
//        max = mode[i];
//      }
//    }
//    return max;
  }

  public static void main(String args[]) {
    int inputNeurons = BOARD_WIDTH * BOARD_HEIGHT;
    int outputNeurons = NUM_NEURONS;

    ADALINEPattern pattern = new ADALINEPattern();
    pattern.setInputNeurons(inputNeurons);
    pattern.setOutputNeurons(outputNeurons);
    BasicNetwork network = (BasicNetwork) pattern.generate();

    // train it

    MLDataSet training = generateTraining();
    MLTrain train = new TrainAdaline(network, training, 0.01);
    
    int epoch = 1;
    do {
      train.iteration();
      System.out.println("Epoch #" + epoch + " Error: " + train.getError());
      epoch++;
    } while (train.getError() > 0.01 && epoch < 100);

    // test it
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
      }

      if (game.redWon()) {
        whoWon = 1;
        redWins++;
      } else if (game.yellowWon()) {
        //yellowWins++;
        whoWon = 2;
      }
      else {
        whoWon = 3;
      }
      if (whoWon == network.winner(board2data(board2string(game))) && game.redWon()) {
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

    try {
      SerializeObject.save(new File("brain/red.net"), network);
    } catch (IOException ex) {
      Logger.getLogger(AdalineDigits.class.getName()).log(Level.SEVERE, null, ex);
    }

    Encog.getInstance().shutdown();
  }
}
