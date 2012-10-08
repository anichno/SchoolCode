
/**
 * Provides a text box and buttons to change its case
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Nov 29, 2011 at 10:04:46 AM
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Problem6 implements ActionListener {

  JTextField funText = null;

  public static void main(String[] args) {
    new Problem6();
  }

  public Problem6() {
    JFrame frame = new JFrame();
    JButton btnUpper = new JButton("Upper case");
    JButton btnLower = new JButton("Lower case");
    funText = new JTextField();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 100);
    frame.setTitle("Silly String Game");
    frame.setLayout(new GridLayout(3, 1, 5, 5));

    btnUpper.addActionListener(this);
    btnLower.addActionListener(this);

    frame.add(btnUpper);
    frame.add(funText);
    frame.add(btnLower);

    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("Upper case")) {
      funText.setText(funText.getText().toUpperCase());
    } else if (e.getActionCommand().equals("Lower case")) {
      funText.setText(funText.getText().toLowerCase());
    }
  }
}
