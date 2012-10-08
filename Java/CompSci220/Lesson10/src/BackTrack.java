import java.util.*;

public class BackTrack 
{
    protected Application app;

   
    /**
     * Initializes this BackTrack object from an application.
     * 
     * @param app the application 
     */    
     public BackTrack (Application app) 
     {
         this.app = app;        
     } // constructor


     /**
       * Attempts to reach a goal through a given position.
       * 
       * @param pos the given position.
       * 
       * @return true if the attempt succeeds; otherwise, false.
       */        
     public boolean tryToReachGoal (Position pos)
     {                
         //System.out.println("declaring iterator");
         Iterator<Position> itr = app.iterator (pos);
         //System.out.println("done.");

         while (itr.hasNext())
         {             
              pos = itr.next();               
              System.out.println("Trying Position ("+pos.getRow()+","+pos.getColumn()+")");
              if (app.isOK (pos))
              {
                  app.markAsPossible (pos);
                  if (app.isGoal (pos) || tryToReachGoal (pos))                  
                       return true;                       
                  app.markAsDeadEnd (pos);                                                                 
              } // pos may be on a path to a goal
         } // while         
         return false;
    } // method tryToReachGoal
    
} // class BackTrack
