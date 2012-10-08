
/**
 * Description: This program draws a fractal curve (recursively)
 *
 * @author  Dr. Brown
 *
 * @version 1.0   Fall 2008
 *
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class Fractal2Curve implements ChangeListener {

  public static final int WINDOW_SIZE = 600;
  public static final int MAX_LEVEL_OF_RECURSION = 8;
  public static final int MAX_HEIGHT = 20;
  //
  private JSpinner levelSpinner;
  private JSpinner heightSpinner;
  private FractalPanel2 centerPanel;

  //-------------------------------------------------------------------
  public static void main(String[] args) {
    new Fractal2Curve();
  }

  //-------------------------------------------------------------------
  public Fractal2Curve() {
    // Create the window
    JFrame frame = new JFrame();
    frame.setLayout(new BorderLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(WINDOW_SIZE, WINDOW_SIZE);
    frame.setTitle("Fractal Curve");

    // Create a graphics panel to draw the fractal curve in.
    centerPanel = new FractalPanel2(); // see class below
    centerPanel.setLayout(null);
    frame.add(centerPanel, BorderLayout.CENTER);

    // The NORTH panel holds the control spinners.
    JPanel topPanel = new JPanel(new FlowLayout());
    frame.add(topPanel, BorderLayout.NORTH);

    JLabel label = new JLabel("Level of recursion:");
    levelSpinner = new JSpinner(new SpinnerNumberModel(8, 1, MAX_LEVEL_OF_RECURSION, 1));
    levelSpinner.addChangeListener(this);

    JLabel label2 = new JLabel("Fractional height: 1/");
    heightSpinner = new JSpinner(new SpinnerNumberModel(4, 2, MAX_HEIGHT, 1));
    heightSpinner.addChangeListener(this);

    topPanel.add(label);
    topPanel.add(levelSpinner);
    topPanel.add(label2);
    topPanel.add(heightSpinner);

    // Make the window visible.
    frame.setVisible(true);
  }

  //-------------------------------------------------------------------
  public void stateChanged(ChangeEvent event) {
    centerPanel.repaint();
  }

//=====================================================================
  class FractalPanel2 extends JPanel {

    private double fraction;
    
    //-------------------------------------------------------------------
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      // Calculate the initial points of a straight horizontal line
      Dimension panelSize = getSize();
      int xOffset = panelSize.width / 30; // 1/30 of panel width
      int yCenter = panelSize.height / 2;
      Point p1 = new Point(xOffset, yCenter);
      Point p2 = new Point(panelSize.width - xOffset, yCenter);

      // Draw the Koch Curve
      fraction = 1.0 / (double) (Integer) heightSpinner.getValue();
      int levelOfRecursion = (Integer) levelSpinner.getValue();
      drawEdge(g, p1, p2, levelOfRecursion);
    }

    //-------------------------------------------------------------------
    public void drawEdge(Graphics g, Point p1, Point p2, int level) {
      if (level <= 1) {
        // Base case; the maximum number of recursive calls has been reached
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
      } else {
        // Calculate 9 interior points between p1 and p2
        int dx = (int) Math.round(0.25 * (p2.x - p1.x));
        int dy = (int) Math.round(0.25 * (p2.y - p1.y));
        int dxHeight = (int) Math.round(fraction * (p2.x - p1.x));
        int dyHeight = (int) Math.round(fraction * (p2.y - p1.y));
        Point p3 = new Point(p1.x + dx, p1.y + dy);
        Point p4 = new Point(p3.x + dyHeight, p3.y - dxHeight);
        Point p5 = new Point(p4.x + dx, p4.y + dy);
        Point p6 = new Point(p5.x - dyHeight, p5.y + dxHeight);
        Point p7 = new Point(p6.x - dyHeight, p6.y + dxHeight);
        Point p8 = new Point(p7.x + dx, p7.y + dy);
        Point p9 = new Point(p8.x + dyHeight, p8.y - dxHeight);

        // Draw the edges
        drawEdge(g, p1, p3, level-1);
        drawEdge(g, p3, p4, level-1);
        drawEdge(g, p4, p5, level-1);
        drawEdge(g, p5, p6, level-1);
        drawEdge(g, p6, p7, level-1);
        drawEdge(g, p7, p8, level-1);
        drawEdge(g, p8, p9, level-1);
        drawEdge(g, p9, p2, level-1);
      }
    }
  }
}
