
/**
 * Final Exam: Problem 7     Limit your time to approximately 23 minutes
 *
 * Grade: _____ / 25
 */
import javax.swing.JOptionPane;

public class Prob07 {

  // 3 pts - implements the GUI as an object
  public static void main(String[] args) {
    String[] options = {"Get Squadron Name",
                        "Do nothing",
                        "Get Squadron Motto"};
    int code = JOptionPane.showOptionDialog(null, "Options:",
                                            "User Input", 0,
                                            JOptionPane.QUESTION_MESSAGE,
                                            null, options, options[0]);

    if (code == 1) {
      System.exit(0);
    } else if (code == 0) {
      String squadName = null;
      do {
        squadName = JOptionPane.showInputDialog("What Name?");
        if (squadName == null) {
          System.exit(0);
        }
      } while (squadName.equals(""));

      System.out.println("Squadron Name: " + squadName);
    } else if (code == 2) {
      String squadMotto = null;
      do {
        squadMotto = JOptionPane.showInputDialog("What Motto?");
        if (squadMotto == null) {
          System.exit(0);
        }
      } while (squadMotto.equals(""));

      System.out.println("Squadron Motto: " + squadMotto);
    }

  }
}
