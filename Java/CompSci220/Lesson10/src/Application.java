import java.util.*;

public interface Application 
{
     /**
      * Determines if a given position is legal and not a dead end.
      *
      * @param pos - the given position.
      *
      * @return true if pos is a legal position and not a dead end.
      */     
     boolean isOK (Position pos);


     /**
      * Indicates that a given position is possibly on a path to a goal.
      *
      * @param pos the position that has been marked as possibly being on a 
      *                path to a goal.
      */
     void markAsPossible (Position pos);


     /**
      * Indicates whether a given position is a goal position.
      *
      * @param pos the position that may or may not be a goal position.
      *
      * @return true if pos is a goal position; false otherwise.
      */ 
     boolean isGoal (Position pos);
 

     /**
      * Indicates that a given position is not on any path to a goal position.
      *
      * @param pos the position that has been marked as not being on any path to 
      *                a goal position.
      */
     void markAsDeadEnd (Position pos);
 

     /**
      * Converts this Application object into a String object.
      *
      * @return the String representation of this Application object.
      */
  @Override
     String toString();


     /**
      * Produces an Iterator object that starts at a given position.
      *
      * @param pos the position the Iterator object starts at.
      *
      * @return an Iterator object that accesses the positions directly
      *               available from pos.             
      */
     Iterator<Position> iterator (Position pos);

} // interface Application
