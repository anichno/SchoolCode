
/**
 * adds more operators to problem 7
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Nov 29, 2011 at 10:29:14 AM
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Problem7modded implements ActionListener {

  JTextField num1 = null;
  JTextField num2 = null;
  JLabel answer = null;
  JComboBox ops = null;

  public static void main(String[] args) {
    new Problem7modded();
  }

  public Problem7modded() {
    JFrame frame = new JFrame();
    JButton btnSolve = new JButton("=");
    JButton btnClear = new JButton("Clear");
    num1 = new JTextField();
    num2 = new JTextField();
    answer = new JLabel();
    String[] operands = new String[]{" + ", " - ", " * ", " / "};
    ops = new JComboBox(operands);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 300);
    frame.setTitle("MegaCalc");
    frame.setLayout(new GridLayout(2, 4, 5, 5));

    btnSolve.addActionListener(this);
    btnClear.addActionListener(this);

    frame.add(num1);
    frame.add(ops);
    frame.add(num2);
    frame.add(btnSolve);
    frame.add(answer);
    frame.add(btnClear);

    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("=")) {
      if (!num1.getText().equals("") && !num2.getText().equals("")) {
        try {
          int temp1 = Integer.parseInt(num1.getText());
          int temp2 = Integer.parseInt(num2.getText());
          switch (ops.getSelectedIndex()) {
            case 0:
              answer.setText(Integer.toString(temp1 + temp2));
              break;
            case 1:
              answer.setText(Integer.toString(temp1 - temp2));
              break;
            case 2:
              answer.setText(Integer.toString(temp1 * temp2));
              break;
            case 3:
              answer.setText(Double.toString((double) temp1 / temp2));
              break;
          }
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
