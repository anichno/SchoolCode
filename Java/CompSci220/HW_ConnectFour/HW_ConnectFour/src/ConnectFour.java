
/**
 * Description: The ConnectFour class is the client or driver program
 * and contains only a main method.
 * 
 * @author Randall.Bower
 */
public class ConnectFour
{
  public static void main( String[] args )
  {
    // The ConnectFourBoard class contains all of the logic for playing
    // the game including making moves, checking for a winner, etc.
    ConnectFourBoard board = new ConnectFourBoard();

    // The ConnectFourUI class contains all of the user interface code including
    // displaying the board and messages to the user and getting moves from the user.
    ConnectFourUI userInterface = new ConnectFourUI();

    // The game continues until there is a winner or the board is full (tie).
    while( !board.isWinner() && !board.isTie() )
    {
      // The entire board is painted each time a move is made.
      userInterface.paint( board );
      
      // Use the ternary operator again to show the appropriate message.
      userInterface.showMessage( board.getPlayerName() + "'s turn." );

      // Ask the user interface to get a move. How exactly this happens is up to the UI.
      int col = userInterface.getMove();
      // Make sure the column selected is not already full.
      while( board.isFull( col ) )
      {
        // The column selected was full, so show an error message and get another column.
        userInterface.showMessage( "Column is full. Please try again." );
        col = userInterface.getMove();
      }

      // Tell the board object that the current player made a move in the selected column.
      board.makeMove( col );
    }

    // Paint the final board after the game is over.
    userInterface.paint( board );
    
    // Show an appropriate message.
    if( board.isWinner() )
    {
      userInterface.showMessage( "Winner!" );
    }
    else
    {
      userInterface.showMessage( "Tie game." );
    }
  }
}
