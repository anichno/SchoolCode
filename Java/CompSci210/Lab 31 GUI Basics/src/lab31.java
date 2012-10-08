
/**
 * asks user about fav number
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Nov 6, 2011 at 3:52:51 PM
 */
import javax.swing.JOptionPane;

public class lab31 {

  public static void main(String[] args) {
    int favNumber = 0;
    String favNumStr = JOptionPane.showInputDialog("What is your favorite number?");
    if (favNumStr != null) {
      favNumber = Integer.parseInt(favNumStr);
    } else {
      System.exit(0);
    }

    int userOption = JOptionPane.showConfirmDialog(null, "Is " + favNumber + " your birthday?");
    if (userOption == 0) {
      JOptionPane.showMessageDialog(null, "Happy Birthday");
    } else if (userOption == 1) {
      JOptionPane.showMessageDialog(null, "Oh well! It must be your favorite number for some other reason");
    } else {
      System.exit(0);
    }
  }
}
