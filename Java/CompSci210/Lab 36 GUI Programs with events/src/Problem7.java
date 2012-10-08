
/**
 * basic calculator
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Nov 29, 2011 at 10:29:14 AM
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Problem7 implements ActionListener {

  JTextField num1 = null;
  JTextField num2 = null;
  JLabel answer = null;

  public static void main(String[] args) {
    new Problem7();
  }

  public Problem7() {
    JFrame frame = new JFrame();
    JButton btnAdd = new JButton("+");
    JButton btnClear = new JButton("Clear");
    num1 = new JTextField();
    num2 = new JTextField();
    answer = new JLabel();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 300);
    frame.setTitle("MegaCalc");
    frame.setLayout(new GridLayout(2, 3, 5, 5));

    btnAdd.addActionListener(this);
    btnClear.addActionListener(this);

    frame.add(num1);
    frame.add(btnAdd);
    frame.add(num2);
    frame.add(answer);
    frame.add(btnClear);

    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("+")) {
      if (!num1.getText().equals("") && !num2.getText().equals("")) {
        try {
          answer.setText(Integer.toString(Integer.parseInt(num1.getText()) + Integer.parseInt(num2.getText())));
        } catch (NumberFormatException exception) {
          answer.setText("Please use numbers");
        }
      }
    } else if (e.getActionCommand().equals("Clear")) {
      num1.setText("");
      num2.setText("");
      answer.setText("");
    }
  }
}
