
/**
 * Description: This class implements the Connect Four User Interface.
 * In this implementation, it uses the DrawingPanel class for all
 * user interaction.
 *
 * @author Randall.Bower
 */
import java.awt.*;

public class ConnectFourUI
{
  /*
   * These constants determine the size of the board on the drawing panel.
   * Note that WIDTH and HEIGHT are based on CELL_SIZE, so to make a
   * larger or smaller board, only change CELL_SIZE.
   */
  private static final int CELL_SIZE = 64;
  private static final int WIDTH = CELL_SIZE * ConnectFourBoard.COLS;
  private static final int HEIGHT = CELL_SIZE * ( ConnectFourBoard.ROWS + 1 );

  private DrawingPanel p; // The DrawingPanel object that will be used to draw the board.
  private Graphics2D g;   // The Graphics object associated with the drawing panel.
  private FontMetrics fm; // A FontMetrics object from the Graphics object to be used
                          // to center the messages above the board.

  /**
   * The constructor creates and opens the DrawingPanel object and sets up the
   * Graphics and FontMetrics objects for future use.
   */
  public ConnectFourUI()
  {
    this.p = new DrawingPanel( WIDTH, HEIGHT );
    this.p.setWindowTitle( "Connect Four" );

    this.g = p.getGraphics();
    this.g.setFont( new Font( Font.SERIF, Font.PLAIN, CELL_SIZE / 3 ) );
    this.fm = g.getFontMetrics();
  }

  /**
   * Paints the entire board and clears the message area at the top.
   */
  public void paint( ConnectFourBoard b )
  {
    p.setBackground( Color.YELLOW );

    // Clear the top area for messages
    g.setColor( Color.WHITE );
    g.fillRect( 0, 0, WIDTH, CELL_SIZE );

    // Square around entire board
    g.setColor( Color.BLACK );
    g.drawRect( 0, CELL_SIZE, WIDTH, HEIGHT );

    // Vertical lines
    for( int c = 1; c < ConnectFourBoard.COLS; c++ )
    {
      g.drawLine( c * CELL_SIZE, CELL_SIZE, c * CELL_SIZE, HEIGHT );
    }

    // Horizontal lines
    for( int r = 2; r <= ConnectFourBoard.ROWS; r++ )
    {
      g.drawLine( 0, r * CELL_SIZE, WIDTH, r * CELL_SIZE );
    }

    // Circles
    int margin = CELL_SIZE / 10;
    for( int r = 1; r <= ConnectFourBoard.ROWS; r++ )
    {
      int y = r * CELL_SIZE;
      for( int c = 1; c <= ConnectFourBoard.COLS; c++ )
      {
        int x = ( c - 1 ) * CELL_SIZE;
        
        // Set the color for the circle, red or blue for a player
        // and white for an empty location.
        if( b.getPlayerAt( r, c ) == 'R' )
        {
          g.setColor( Color.RED );
        }
        else if( b.getPlayerAt( r, c ) == 'B' )
        {
          g.setColor( Color.BLUE );
        }
        else
        {
          g.setColor( Color.WHITE );
        }

        // Fill the circle with the appropriate color.
        g.fillOval( x + margin, y + margin, CELL_SIZE - margin * 2, CELL_SIZE - margin * 2 );

        // Draw a black outline around the filled circle.
        g.setColor( Color.BLACK );
        g.drawOval( x + margin, y + margin, CELL_SIZE - margin * 2, CELL_SIZE - margin * 2 );

        x += CELL_SIZE;
      }
    }
    
    p.copyGraphicsToScreen();
  }

  /**
   * Shows the given message in the area above the board.
   * 
   * @param message Message to be shown.
   */
  public void showMessage( String message )
  {
    // Clear the message area.
    g.setColor( Color.WHITE );
    g.fillRect( 0, 0, WIDTH, CELL_SIZE - 1 );

    // Calculate x,y coordinates so message is centered.
    int x = WIDTH / 2 - fm.stringWidth( message ) / 2;
    int y = CELL_SIZE * 2 / 3;

    g.setColor( Color.BLACK );
    g.drawString( message, x, y );
    p.copyGraphicsToScreen();
  }

  /**
   * Uses the DrawingPanel's mouse click method to wait for a click,
   * then calculates and returns the column that was clicked.
   * 
   * @return The column that was clicked.
   */
  public int getMove()
  {
    p.waitForMouseClick( DrawingPanel.LEFT_BUTTON );

    return 1 + p.getMouseClickX( DrawingPanel.LEFT_BUTTON ) / CELL_SIZE;
  }
}
