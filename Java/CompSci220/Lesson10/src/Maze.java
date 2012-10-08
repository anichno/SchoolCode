import java.util.*;

public class Maze implements Application {

     public static final byte WALL = 0;
     public static final byte CORRIDOR = 1;
     public static final byte PATH = 9;
     public static final byte DEAD_END = 2;

     protected Position start,
                        finish;
          
     protected byte[ ][ ] grid;

     /**
      * Initializes this Maze object from a file scanner over a file.
      *
      * @param fileScanner - the scanner over the file that holds the
      *                      maze information.
      * 
      * @throws InputMismatchException - if any of the row or column values are non-
      *                                 integers, or if any of the grid entries are non-integers.
      * @throws NumberFormatException - if the grid entries are integers but neither
      *                                                            WALL nor CORRIDOR  
      */     
     public Maze (Scanner fileScanner) 
     {
          int rows = fileScanner.nextInt(),
              columns = fileScanner.nextInt();
          
          grid = new byte [rows][columns];               
          start = new Position (fileScanner.nextInt(), fileScanner.nextInt());                
          finish = new Position (fileScanner.nextInt(), fileScanner.nextInt());     
          for (int i = 0; i < rows; i++)                                   
               for (int j = 0; j < columns; j++)
               {
                   grid [i][j] = fileScanner.nextByte();
                   if (grid [i][j] != WALL && grid [i][j] != CORRIDOR)
                       throw new NumberFormatException ("At position (" + i + ", " + j + "), " +
                                                     grid [i][j] + " should be " +
                                                     WALL + " or " + CORRIDOR + ".");
               } // for j
      } // constructor


     public Position getStart()
     {
         return start;
     } // method getStart
     
     public Position getFinish()
     {
         return finish;
     } // method getFinish
     
     /**
      * Returns a 2-dimensional array that holds a copy of the maze configuration.
      *      
      * @return - a 2-dimensional array that holds a copy of the maze configuration.
      * 
      */    
     public byte[][] getGrid()
     {
       byte[][] gridCopy = new byte[grid.length][grid[0].length];
       
       for (int i = 0; i < grid.length; i++)
         for (int j = 0; j < grid[i].length; j++)
            gridCopy[i][j] = grid[i][j];
              
       return gridCopy;
     } // method gridCopy

     /**
      * Determines if a given position is legal and not a dead end.
      *
      * @param pos - the given position.
      *
      * @return true if pos is a legal position and not a dead end.
      */     
  @Override
      public boolean isOK (Position pos)
      { 
         return pos.getRow() >= 0 && pos.getRow() < grid.length &&
                  pos.getColumn() >= 0 && pos.getColumn() < grid [0].length &&
                  grid [pos.getRow()][pos.getColumn()] == CORRIDOR;
      } // method isOK


      /**
       * Indicates that a given position is possibly on a path to a goal.
       *
       * @param pos the position that has been marked as possibly being on a path
       *            to a goal.
       */
  @Override
       public void markAsPossible (Position pos)
       {
           grid [pos.getRow()][pos.getColumn()] = PATH;
       } // method markAsPossible


      /**
       * Indicates whether a given position is a goal position.
       *
       * @param pos the position that may or may not be a goal position.
       *
       * @return true if pos is a goal position; false otherwise.
       */ 
  @Override
       public boolean isGoal (Position pos)
       {
           return pos.getRow() == finish.getRow() && 
                  pos.getColumn() == finish.getColumn();
       } // method isGoal


      /**
       * Indicates that a given position is not on any path to a goal position.
       *
       * @param pos the position that has been marked as not being on any path to a
       *            goal position.
       */
  @Override
       public void markAsDeadEnd (Position pos)
       {
           grid [pos.getRow()][pos.getColumn()] = DEAD_END;
       } // method markAsDeadEnd


      /**
       * Converts this Application object into a String object.
       *
       * @return the String representation of this Application object.
       */
  @Override
       public String toString () 
       {
           String result = "\n";

           for (int row = 0; row < grid.length; row++) 
           {
                for (int column = 0; column < grid [0].length; column++)
                     result += String.valueOf (grid [row][column]) + ' ';
                result += "\n";
           } // for row = 0
           return result;
        } // method toString


      /**
       * Produces an Iterator object, over elements of type Position, that starts at a given 
       * position.
       *
       * @param pos - the position the Iterator object starts at.
       *
       * @return the Iterator object.
       */
  @Override
       public Iterator<Position> iterator (Position pos) 
       {
           return new MazeIterator (pos);
       } // method iterator


       protected class MazeIterator implements Iterator<Position> 
       {
           protected static final int MAX_MOVES = 4;

           protected int row,
                         column,
                         count;

          /**
           * Initializes this MazeIterator object to start at a given position.
           *
           * @param pos the position the Iterator objects starts at.
           */
           public MazeIterator (Position pos) 
           {
               row = pos.getRow();
               column = pos.getColumn();
               count = 0;
           } // constructor


          /**
           * Determines if this MazeIterator object can advance to another
           * position.
           *
           * @return true if this MazeIterator object can advance; false otherwise.
           */
    @Override
           public boolean hasNext () 
           {
                return count < MAX_MOVES;
           } // method hasNext


          /**
           * Advances this MazeIterator object to the next position.
           *
           * @return the position advanced to.
           */  
    @Override
           public Position next () 
           {
                Position nextPosition = new Position();
                switch (count++) 
                {
                  case 0: nextPosition = new Position (row-1, column); // north
                          break;
                  case 1: nextPosition = new Position (row, column+1); // east
                          break;
                  case 2: nextPosition = new Position (row+1, column); // south
                            break;
                  case 3: nextPosition = new Position (row, column-1); // west
                } // switch;                
                return nextPosition;
           } // method next
  
    @Override
           public void remove () 
           { 
                // removal is illegal for a MazeIterator object
                throw new UnsupportedOperationException(); 
            } // method remove

        } // class MazeIterator

} // class Maze 
