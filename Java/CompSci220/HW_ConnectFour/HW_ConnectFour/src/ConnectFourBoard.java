
/**
 * Description: The ConnectFourBoard class is a logical representation
 * of the game board. It uses a two-dimensional array of characters.
 *
 * @author Randall.Bower
 */
public class ConnectFourBoard
{
  /** A standard Connect Four board has six rows. */
  public static final int ROWS = 6;
  /** A standard Connect Four board has seven columns. */
  public static final int COLS = 7;
  /** Used to indicate no moves have been made on this board. */
  public static final int NO_MOVES_YET = -1;

  private char[][] board; // Two-dimensional array keeping track of all moves.
  private char player;    // Indicates the current player.
  private int lastMove;   // This variable keeps track of the column of the last move.

  /**
   * Constructor allocates the array and resets the game to a beginning state.
   */
  public ConnectFourBoard()
  {
    // NOTE:
    // Users prefer row and column numbers starting at 1, so add an extra row and
    // column to the array and ignore row 0 and column 0.  A bit of wasted memory,
    // but reasonably insignificant compared to the increased user-friendliness.
    this.board = new char[ ROWS + 1 ][ COLS + 1 ];
    
    // The reset functionality is in a separate method so a client program could
    // be written to play multiple games without creating a new board each time.
    reset();
  }

  /**
   * Resets the board to a new game state.
   */
  public final void reset()
  {
    for( int r = 1; r <= ROWS; r++ )
    {
      for( int c = 1; c <= COLS; c++ )
      {
        this.board[r][c] = ' ';
      }
    }
    this.lastMove = NO_MOVES_YET;
    this.player = 'R'; // Red player always starts.
  }

  /**
   * Gets the player at the indicated place on the board.
   * 
   * @param row Row of the player to be fetched.
   * @param col Column of the player to be fetched.
   * @return The character representing the player at this location
   *         or a space if no move had been made in this location.
   */
  public char getPlayerAt( int row, int col )
  {
    if( row < 1 || row > ROWS )
    {
      throw new IllegalArgumentException( "Invalid row: " + row + ". Must be between 1 and " + ROWS );
    }

    if( col < 1 || col > COLS )
    {
      throw new IllegalArgumentException( "Invalid column: " + col + ". Must be between 1 and " + COLS );
    }

    return this.board[row][col];
  }
  
  public String getPlayerName()
  {
    if( this.player == 'R' )
    {
      return "Red";
    }
    else
    {
      return "Blue";
    }
  }

  /**
   * Makes a move with the indicated player in the indicated column.
   * 
   * @param col Column in which to make a move.
   * @param player Character representing the player making the move.
   */
  public void makeMove( int col )
  {
    if( col < 1 || col > COLS )
    {
      throw new IllegalArgumentException( "Invalid column: " + col + ". Must be between 1 and " + COLS );
    }

    if( this.isFull( col ) )
    {
      throw new RuntimeException( "Invalid move: Column " + col + " is full." );
    }

    // Find the top-most empty row.
    int row = ROWS;
    while( this.board[row][col] != ' ' )
    {
      row--;
    }

    this.board[row][col] = this.player;  // Make the move.
    this.lastMove = col;  // Remember this column as the last move made.
    this.player = ( this.player == 'R' ) ? 'B' : 'R';  // Change players.
  }

  /**
   * Determines if the given column is full.
   * 
   * @param col Column to check for fullness.
   * @return True if the column is full; false otherwise.
   */
  public boolean isFull( int col )
  {
    // If the first row in the column is not a space, then the column is full.
    return this.board[ 1 ][ col ] != ' ';
  }

  /**
   * Determine if the current state of the game is a tie. The game is a tie
   * if there is not a winner and the board is full.
   * 
   * @return True if the game is a tie; false otherwise.
   */
  public boolean isTie()
  {
    // Look through each column to see if there is one with room.
    for( int c = 1; c <= COLS; c++ )
    {
      // If the first row is empty, then the board is not full so
      // the game cannot be a tie.
      if( this.board[1][c] == ' ' )
      {
        return false;
      }
    }

    // All columns were full, so board is full. The game is now a tie if
    // there is not a winner.
    return !isWinner();
  }

  /**
   * Determine if the last move made on the board resulted in a winner.
   * The winning move is only checked from the last move made in the game.
   * If there were a winning combination elsewhere on the board, the game
   * should have stopped when that move was made.
   * 
   * @return True if the last move made on the board resulted in a winner.
   */
  public boolean isWinner()
  {
    // No moves yet, so no winner.
    if( this.lastMove == NO_MOVES_YET )
    {
      return false;
    }

    // Determine the exact location of the last move made.
    int c = lastMove;
    int r = 1;
    while( r <= ROWS && this.board[r][c] == ' ' )
    {
      r++;
    }

    // Check all thirteen possibilities from the last move made.
    return // Straight down
            r <= ROWS - 4
            && this.board[r][c] == this.board[r + 1][c]
            && this.board[r][c] == this.board[r + 2][c]
            && this.board[r][c] == this.board[r + 3][c]
            || // Three to the right
            c <= COLS - 4
            && this.board[r][c] == this.board[r][c + 1]
            && this.board[r][c] == this.board[r][c + 2]
            && this.board[r][c] == this.board[r][c + 3]
            || // One left, two right
            c > 1 && c <= COLS - 2
            && this.board[r][c] == this.board[r][c - 1]
            && this.board[r][c] == this.board[r][c + 1]
            && this.board[r][c] == this.board[r][c + 2]
            || // Two left, one right
            c > 2 && c <= COLS - 1
            && this.board[r][c] == this.board[r][c - 1]
            && this.board[r][c] == this.board[r][c - 2]
            && this.board[r][c] == this.board[r][c + 1]
            || // Three left
            c > 3
            && this.board[r][c] == this.board[r][c - 1]
            && this.board[r][c] == this.board[r][c - 2]
            && this.board[r][c] == this.board[r][c - 3]
            || // Three down and right
            r <= ROWS - 3 && c <= COLS - 3
            && this.board[r][c] == this.board[r + 1][c + 1]
            && this.board[r][c] == this.board[r + 2][c + 2]
            && this.board[r][c] == this.board[r + 3][c + 3]
            || // One up and left, two down and right
            r > 1 && r <= ROWS - 2 && c > 1 && c <= COLS - 2
            && this.board[r][c] == this.board[r - 1][c - 1]
            && this.board[r][c] == this.board[r + 1][c + 1]
            && this.board[r][c] == this.board[r + 2][c + 2]
            || // Two up and left, one down and right
            r > 1 && r <= ROWS - 2 && c > 1 && c <= COLS - 2
            && this.board[r][c] == this.board[r - 1][c - 1]
            && this.board[r][c] == this.board[r + 1][c + 1]
            && this.board[r][c] == this.board[r + 2][c + 2]
            || // Three up and left
            r > 3 && c > 3
            && this.board[r][c] == this.board[r - 1][c - 1]
            && this.board[r][c] == this.board[r - 2][c - 2]
            && this.board[r][c] == this.board[r - 3][c - 3]
            || // Three up and right
            r > 3 && c <= COLS - 3
            && this.board[r][c] == this.board[r - 1][c + 1]
            && this.board[r][c] == this.board[r - 2][c + 2]
            && this.board[r][c] == this.board[r - 3][c + 3]
            || // One down and left, two up and right
            r > 2 && r <= ROWS - 1 && c > 1 && c <= COLS - 2
            && this.board[r][c] == this.board[r + 1][c - 1]
            && this.board[r][c] == this.board[r - 1][c + 1]
            && this.board[r][c] == this.board[r - 2][c + 2]
            || // Two down and left, one up and right
            r > 1 && r <= ROWS - 2 && c > 2 && c <= COLS - 1
            && this.board[r][c] == this.board[r + 1][c - 1]
            && this.board[r][c] == this.board[r + 2][c - 2]
            && this.board[r][c] == this.board[r - 1][c + 1]
            || // Three down and left
            r <= ROWS - 3 && c > 3
            && this.board[r][c] == this.board[r + 1][c - 1]
            && this.board[r][c] == this.board[r + 2][c - 2]
            && this.board[r][c] == this.board[r + 3][c - 3];
  }

  /**
   * Creates and returns a string representation of the board.
   */
  @Override
  public String toString()
  {
    String s = "";
    for( int r = 1; r <= ROWS; r++ )
    {
      for( int c = 1; c <= COLS; c++ )
      {
        s += this.board[r][c];
      }
      s += '\n';
    }
    return s;
  }
}
