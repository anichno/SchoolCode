
/**
 * <put a program description here>
 * 
 * @author anichno
 * 
 * @version 1.0 - Nov 7, 2011 at 10:11:42 PM
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.encog.ml.data.MLData;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.neural.networks.BasicNetwork;
import org.encog.util.obj.SerializeObject;

public class ComputerPlayer {
  private final static int BOARD_WIDTH = 7;
  private final static int BOARD_HEIGHT = 5;
  private BasicNetwork network = null;

  public ComputerPlayer() {
    try {
      network = (BasicNetwork) SerializeObject.load(new File("brain.net"));
    } catch (IOException ex) {
      Logger.getLogger(ComputerPlayer.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(ComputerPlayer.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public int getMove(Connect4 game) {
    if (board2string(game).equals("0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ")) {
      return (int) (Math.random() * 7) + 1;
    }
    return network.winner(board2data(board2string(game)))+1;
  }
  
  public static String board2string(Connect4 game) {
    String tempGame = "";
    for (int index = 0; index < 5; index++) {
      int[] board = game.getRawBoard(index);
      for (int j = 0; j < board.length; j++) {
        tempGame += board[j] + " ";
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
