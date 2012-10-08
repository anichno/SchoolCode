
/**
 * builds a messenger window
 * 
 * @author anichno
 * 
 * @version 1.0 - Nov 8, 2011 at 9:15:51 PM
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class exercise5 {

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(new Dimension(300, 200));
    frame.setTitle("Compose Message");

    frame.setLayout(new BorderLayout());

    JPanel topPanel = new JPanel(new GridLayout(2, 1));
    topPanel.add(new JLabel("To:"));
    topPanel.add(new JTextField(10));
    topPanel.add(new JLabel("CC:"));
    topPanel.add(new JTextField(10));
    frame.add(topPanel, BorderLayout.NORTH);

    JPanel centerPanel = new JPanel(new GridLayout());
    centerPanel.add(new JTextField(10));
    frame.add(centerPanel, BorderLayout.CENTER);

    JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
    bottomPanel.add(new JLabel());
    bottomPanel.add(new JButton("Send"));
    frame.add(bottomPanel, BorderLayout.SOUTH);

    frame.setVisible(true);
  }
}
