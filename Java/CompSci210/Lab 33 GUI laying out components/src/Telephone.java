
/**
 * Description: A GUI that resembles a telephone keypad. 
 *
 * @author Textbook, pages 744-745
 *
 * @version 1.0   Fall 2008
 */
import java.awt.*;
import javax.swing.*;

public class Telephone {

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(new Dimension(250, 200));
    frame.setTitle("Telephone");

    frame.setLayout(new BorderLayout());

    // the main phone buttons
    JPanel centerPanel = new JPanel(new GridLayout(4, 3, 5, 5));
    for (int j = 1; j <= 9; j++) {
      centerPanel.add(new JButton("" + j));
    }
    centerPanel.add(new JButton("*"));
    centerPanel.add(new JButton("0"));
    centerPanel.add(new JButton("#"));
    frame.add(centerPanel, BorderLayout.CENTER);

    // south status panel
    JPanel southPanel = new JPanel(new GridLayout(2, 1));
    southPanel.add(new JLabel("Number to dial: "));
    southPanel.add(new JTextField(10));
    frame.add(southPanel, BorderLayout.WEST);

    frame.setVisible(true);
  }
}
