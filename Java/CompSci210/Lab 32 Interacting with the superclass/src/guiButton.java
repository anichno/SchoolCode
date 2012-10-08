
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Point;
import javax.swing.JLabel;

/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Nov 6, 2011 at 6:58:28 PM
 */
public class guiButton {

  public static void main(String[] args) {
    new coolFrame();
  }
}

class MultiLineButton extends JButton {

  public MultiLineButton(String textLine1, String textLine2) {
    super();
    super.setLayout(new GridLayout(2, 1, 0, 5));
    super.add(new JLabel(textLine1));
    super.add(new JLabel(textLine2));
    super.setActionCommand(textLine1 + ":" + textLine2);
  }
}

class coolFrame extends JFrame implements ActionListener {

  public coolFrame() {
    // Step 1: Create a JFrame object
    JFrame frame = new JFrame();

    // Step 2: Set the JFrame properties (many more properties are posssible)
    frame.setForeground(Color.WHITE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocation(new Point(10, 50));
    frame.setSize(new Dimension(300, 120));
    frame.setTitle("A frame");
    frame.setLayout(new FlowLayout());
    JButton b1 = new JButton("Button 1");
    JButton b2 = new MultiLineButton("Line 1", "Line 2");
    frame.add(b1);
    frame.add(b2);
    b1.addActionListener(this);
    b2.addActionListener(this);

    // Step 3: Make the JFrame visible on the screen
    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("Button 1")) {
      System.out.println("You clicked Button 1");
    }
    if (e.getActionCommand().equals("Line 1:Line 2")) {
      System.out.println("You clicked Button 2");
    }
  }
}