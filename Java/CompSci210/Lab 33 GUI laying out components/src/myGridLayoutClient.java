
/**
 * uses telephone with myGridLayout
 * 
 * @author anichno
 * 
 * @version 1.0 - Nov 8, 2011 at 10:11:07 PM
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class myGridLayoutClient {

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(new Dimension(300, 200));
    frame.setTitle("Telephone");

    frame.setLayout(new BorderLayout());

    // the main phone buttons
    JPanel centerPanel = new JPanel(new myGridLayout(4, 3));
    for (int j = 1; j <= 9; j++) {
      centerPanel.add(new JButton("" + j));
    }
    centerPanel.add(new JButton("*"));
    centerPanel.add(new JButton("0"));
    centerPanel.add(new JButton("#"));
    frame.add(centerPanel, BorderLayout.CENTER);

    // south status panel
    JPanel southPanel = new JPanel(new FlowLayout());
    southPanel.add(new JLabel("Number to dial: "));
    southPanel.add(new JTextField(10));
    frame.add(southPanel, BorderLayout.SOUTH);

    frame.setVisible(true);
  }
}
