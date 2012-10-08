
/**
 * Lab 27 Exercise - Tic-Tac-Toe Class
 * Class Interface:
 * - TicTacToe() - default constructor
 * - nextMove(int) - makes next move at 1-9 (X always moves first)
 * - xIsAt(int) - returns true if there is an X at the given position
 * - oIsAt(int) - returns true if there is an O at the given position
 * - toString() - returns a string representation of the game state
 * - xWon() - returns true if X has won the game
 * - oWon() - returns true if O has won the game
 * - isTie() - returns true if all squares are filled with no winner
 * 
 * @author *** PUT YOUR TEAM'S NAMES HERE
 * 
 * @version 1.0 - Oct 21, 2011 at 8:03:57 PM
 */
public class TicTacToe {

  private char[][] board = new char[3][3];
  private boolean turn = true;

  public TicTacToe() {
    for (int counter = 0; counter < board[0].length; counter++) {
      for (int counter2 = 0; counter2 < board[0].length; counter2++) {
        board[counter][counter2] = ' ';
      }
    }
  }

  public void nextMove(int position) {
    int row = (position - 1) % 3;
    if (xIsAt(position) || oIsAt(position)) {
      return;
    }

    if (turn) {
      board[(position - 1) / 3][row] = 'X';
    } else {
      board[(position - 1) / 3][row] = 'O';
    }
    turn = !turn;
  }

  public boolean xIsAt(int position) {
    int row = (position - 1) % 3;
    if (board[(position - 1) / 3][row] == 'X') {
      return true;
    }
    return false;
  }

  public boolean oIsAt(int position) {
    int row = (position - 1) % 3;
    if (board[(position - 1) / 3][row] == 'O') {
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    String gameBoard = "";
    for (int counter = 0; counter < board[0].length; counter++) {
      for (int counter2 = 0; counter2 < board[0].length; counter2++) {
        if ((counter2 + 1) % 2 == 0) {
          gameBoard += "|" + board[counter][counter2] + "|";
        } else {
          gameBoard += board[counter][counter2];
        }
      }
      gameBoard += '\n';
      if (counter != board[0].length - 1) {
        gameBoard += "-----\n";
      }
    }
    return gameBoard;
  }

  public boolean xWon() {
    boolean win = false;
    
    //  check horizontal
    for (int counter = 0; counter < board[0].length; counter++) {
      win = false;
      for (int counter2 = 0, vert = 1; counter2 < board[0].length; counter2++, vert += 3) {
        if (xIsAt(counter + counter2 + 1)) {
          win = true;
        } else {
          win = false;
          break;
        }
      }
      if (win)
        return true;
    }
    
    
    //  check vertical
    for (int counter = 0; counter < board[0].length; counter++) {
      win = false;
      for (int counter2 = 0, vert = 1; counter2 < board[0].length; counter2++, vert += 3) {
        if (xIsAt(counter + vert)) {
          win = true;
        } else {
          win = false;
          break;
        }
      }
      if (win)
        return true;
    }
    
    //  check diag
    if (xIsAt(1) && xIsAt(9)) {
    for (int counter = 0; counter < board[0].length; counter++) {
      win = false;
      for (int counter2 = 0, diag = 1; counter2 < board[0].length; counter2++, diag += 4) {
        if (xIsAt(counter + diag)) {
          win = true;
        } else {
          win = false;
          break;
        }
      }
      if (win)
        return true;
    }
    }
    else if (xIsAt(3) && xIsAt(7)) {
      for (int counter = 0; counter < board[0].length; counter++) {
        win = false;
      for (int counter2 = 0, diag = 3; counter2 < board[0].length; counter2++, diag += 2) {
        if (xIsAt(counter + diag)) {
          win = true;
        } else {
          win = false;
          break;
        }
      }
      if (win)
        return true;
    }
    }
    return win;

  }

  public boolean oWon() {
    boolean win = false;
    
    //  check horizontal
    for (int counter = 0; counter < board[0].length; counter++) {
      win = false;
      for (int counter2 = 0, vert = 1; counter2 < board[0].length; counter2++, vert += 3) {
        if (oIsAt(counter + counter2 + 1)) {
          win = true;
        } else {
          win = false;
          break;
        }
      }
      if (win)
        return true;
    }
    
    //  check vertical
    for (int counter = 0; counter < board[0].length; counter++) {
      win = false;
      for (int counter2 = 0, vert = 1; counter2 < board[0].length; counter2++, vert += 3) {
        if (oIsAt(counter + vert)) {
          win = true;
        } else {
          win = false;
          break;
        }
      }
      if (win)
        return true;
    }
    
    //  check diag
    if (oIsAt(1) && oIsAt(9)) {
    for (int counter = 0; counter < board[0].length; counter++) {
      win = false;
      for (int counter2 = 0, diag = 1; counter2 < board[0].length; counter2++, diag += 4) {
        if (oIsAt(counter + diag)) {
          win = true;
        } else {
          win = false;
          break;
        }
      }
      if (win)
        return true;
    }
    }
    else if (oIsAt(3) && oIsAt(7)) {
      for (int counter = 0; counter < board[0].length; counter++) {
        win = false;
      for (int counter2 = 0, diag = 3; counter2 < board[0].length; counter2++, diag += 2) {
        if (oIsAt(counter + diag)) {
          win = true;
        } else {
          win = false;
          break;
        }
      }
      if (win)
        return true;
    }
    }
    return win;

  }

  public boolean isTie() {
    int totOnBoard = 0;
    for (int counter = 0; counter < board[0].length*board[0].length && !xWon() && !oWon(); counter++) {
      if (xIsAt(counter + 1) || oIsAt(counter+1))
        totOnBoard++;
    }
    if (totOnBoard == 9)
      return true;
    return false;
  }
}