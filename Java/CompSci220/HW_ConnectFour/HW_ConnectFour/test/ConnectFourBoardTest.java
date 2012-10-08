
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Randall.Bower
 */
public class ConnectFourBoardTest
{
  /**
   * Test of isFull method, of class ConnectFourBoard.
   */
  @Test
  public void testIsFull()
  {
    System.out.println( "Testing isFull()..." );
    ConnectFourBoard b = new ConnectFourBoard();
    for( int col = 1; col <= ConnectFourBoard.COLS; col ++ )
    {
      b.reset(); // Reset each time to make sure there isn't accidentally a winner.
                 // Though this wouldn't really matter since we're not calling isWinner here.
      for( int row = 1; row <= ConnectFourBoard.ROWS; row++ )
      {
        b.makeMove( col );
      }
      assertTrue( b.isFull( col ) );
    }
  }

  /**
   * Test of isTie method, of class ConnectFourBoard.
   */
  @Test
  public void testIsTie()
  {
    System.out.println( "Testing isTie()..." );
    ConnectFourBoard b = new ConnectFourBoard();
    int[] moves = { 1, 1, 2, 2, 3, 3, 5, 5, 6, 6, 7, 7, 1, 4, 4, 1, 2, 2, 3, 3, 5, 5, 6, 6, 7, 7, 1, 4, 4, 1, 2, 2, 3, 3, 7, 7, 6, 6, 5, 4, 4, 5 };
    for( int i = 0; i < moves.length; i++ )
    {
      b.makeMove( moves[ i ] );
    }
    assertTrue( b.isTie() );
    assertFalse( b.isWinner() );
  }

  /**
   * Test of isWinner method, of class ConnectFourBoard.
   */
  @Test
  public void testIsWinner()
  {
    System.out.println( "Testing isWinner()..." );
    ConnectFourBoard b = new ConnectFourBoard();
    int[] moves = { 2, 2, 3, 3, 4, 4, 5 };
    for( int i = 0; i < moves.length; i++ )
    {
      b.makeMove( moves[ i ] );
    }
    assertTrue( b.isWinner() );
    assertFalse( b.isTie() );
  }
  
  // TODO: Remove the test below before giving to students!!
  
  /**
   * Test of isWinner method, of class ConnectFourBoard.
   */
  @Test
  public void testIsWinnerDiagonal()
  {
    System.out.println( "Testing isWinner()..." );
    ConnectFourBoard b = new ConnectFourBoard();
    int[] moves = { /* what to put here??? */ };
    for( int i = 0; i < moves.length; i++ )
    {
      b.makeMove( moves[ i ] );
    }
    assertTrue( b.isWinner() );
    assertFalse( b.isTie() );
  }
  
  /**
   * Test of getPlayerAt method, of class ConnectFourBoard.
   */
  @Test
  public void testGetPlayerAt() {
    System.out.println("getPlayerAt");
    int row = 1;
    int col = 1;
    ConnectFourBoard instance = new ConnectFourBoard();
    char expResult = ' ';
    char result = instance.getPlayerAt(row, col);
    assertEquals(expResult, result);
  }

  /**
   * Test of makeMove method, of class ConnectFourBoard.
   */
  @Test
  public void testMakeMove() {
    System.out.println("makeMove");
    int col = 1;
    ConnectFourBoard instance = new ConnectFourBoard();
    instance.makeMove(col);
  }

}
