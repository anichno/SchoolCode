
/**
 * Final Exam: Problem 8     Limit your time to approximately 32 minutes
 *
 * Grade: _____ / 35
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Prob08 implements ActionListener {

  JFrame frame;
  JButton goButton;
  static JPanel sketchPad;
  static Graphics2D g;

  public static void main(String[] args) {
    new Prob08();
    g = (Graphics2D) sketchPad.getGraphics();
  }

  public Prob08() {
    frame = new JFrame();
    frame.setForeground(Color.WHITE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocation(10, 50);
    frame.setSize(400, 200);
    frame.setTitle("Random");
    frame.setLayout(new BorderLayout());

    goButton = new JButton("Go");
    goButton.addActionListener(this);
    frame.add(goButton, BorderLayout.NORTH);

    sketchPad = new JPanel();
    frame.add(sketchPad, BorderLayout.CENTER);
    sketchPad.setBackground(Color.white);

    frame.setVisible(true);
  }

  public void playGame() {
    int[] nums = new int[3];

    g.setColor(Color.white);
    g.fillRect(0, 0, 400, 200);

    for (int i = 0; i < 3; i++) {
      nums[i] = (int) Math.round(Math.random()) + 4;
    }

    g.setColor(Color.black);
    if (nums[0] == nums[1] && nums[0] == nums[2]) {
      g.drawString("Winner", 170, 20);
    } else {
      g.drawString("You lose", 170, 20);
    }

    g.setColor(Color.red);
    g.fillOval(30, 30, 75, 75);
    g.fillOval(150, 30, 75, 75);
    g.fillOval(270, 30, 75, 75);

    g.setColor(Color.black);
    g.drawString(Integer.toString(nums[0]), 65, 70);
    g.drawString(Integer.toString(nums[1]), 185, 70);
    g.drawString(Integer.toString(nums[2]), 305, 70);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    playGame();
  }
}
