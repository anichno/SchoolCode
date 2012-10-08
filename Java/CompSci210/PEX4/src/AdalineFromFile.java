
/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Nov 7, 2011 at 5:20:55 PM
 */
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.encog.ml.data.MLData;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.neural.networks.BasicNetwork;
import org.encog.util.obj.SerializeObject;

public class AdalineFromFile {
  public final static int CHAR_WIDTH = 5;
	public final static int CHAR_HEIGHT = 7;
	
	public static String[][] DIGITS = { 
      { " OOO ",
        "O   O",
        "O   O",
        "O   O",
        "O   O",
        "O   O",
        " OOO "  },

      { "  O  ",
        " OO  ",
        "O O  ",
        "  O  ",
        "  O  ",
        "  O  ",
        "  O  "  },

      { " OOO ",
        "O   O",
        "    O",
        "   O ",
        "  O  ",
        " O   ",
        "OOOOO"  },

      { " OOO ",
        "O   O",
        "    O",
        " OOO ",
        "    O",
        "O   O",
        " OOO "  },

      { "   O ",
        "  OO ",
        " O O ",
        "O  O ",
        "OOOOO",
        "   O ",
        "   O "  },

      { "OOOOO",
        "O    ",
        "O    ",
        "OOOO ",
        "    O",
        "O   O",
        " OOO "  },

      { " OOO ",
        "O   O",
        "O    ",
        "OOOO ",
        "O   O",
        "O   O",
        " OOO "  },

      { "OOOOO",
        "    O",
        "    O",
        "   O ",
        "  O  ",
        " O   ",
        "O    "  },

      { " OOO ",
        "O   O",
        "O   O",
        " OOO ",
        "O   O",
        "O   O",
        " OOO "  },

      { " OOO ",
        "O   O",
        "O   O",
        " OOOO",
        "    O",
        "O   O",
        " OOO "  } };

  public static void main(String[] args) {
    BasicNetwork network = null;
    System.out.println("loading\r\n");
    try {
      network = (BasicNetwork) SerializeObject.load(new File("test.net"));
    } catch (IOException ex) {
      Logger.getLogger(AdalineFromFile.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(AdalineFromFile.class.getName()).log(Level.SEVERE, null, ex);
    }
    // test it
		for(int i=0;i<DIGITS.length;i++)
		{
			int output = network.winner(image2data(DIGITS[i]));
			
			for(int j=0;j<CHAR_HEIGHT;j++)
			{
				if( j==CHAR_HEIGHT-1 )
					System.out.println(DIGITS[i][j]+" -> "+output);
				else
					System.out.println(DIGITS[i][j]);
				
			}
  }
}
  
  public static MLData image2data(String[] image)
	{
		MLData result = new BasicMLData(CHAR_WIDTH*CHAR_HEIGHT);
		
		for(int row = 0; row<CHAR_HEIGHT; row++)
		{
			for(int col = 0; col<CHAR_WIDTH; col++)
			{
				int index = (row*CHAR_WIDTH) + col;
				char ch = image[row].charAt(col);
				result.setData(index,ch=='O'?1:-1 );
			}
		}
		
		return result;
	}
}
