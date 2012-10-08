import java.io.*;
import java.util.*;

public class MazeUser
{
    public static void main (String[ ] args)
    {
        new MazeUser().run();
    } // method main
    
    public void run()
    {                    
        final String INPUT_PROMPT =
            "\n\nPlease enter the path for the file whose first line contains the " +
            "number of rows and columns,\nwhose 2nd line the start row and column, " +
            "whose 3rd line the finish row and column, and then the maze, row-by-row: ";

        final String INITIAL_STATE =
            "\nThe initial state is as follows (0 = WALL, 1 = CORRIDOR):\n";

        final String START_INVALID = "The start position is invalid.";

        final String FINISH_INVALID = "The finish position is invalid.";

        final String FINAL_STATE =
            "The final state is as follows (2 = DEAD END, 9 = PATH):\n";

        final String SUCCESS = "\n\nA solution has been found:";
        
        final String FAILURE = "\n\nThere is no solution:";

        Maze maze = null;
        
        Scanner keyboardScanner = new Scanner (System.in),
                fileScanner = null;

        String fileName;
        
        while (true)
        {
            try
            {
                System.out.print (INPUT_PROMPT);  
                fileName = keyboardScanner.next();
                fileScanner = new Scanner (new File (fileName));
                break;
            } // try
            catch (IOException e)
            {
                System.out.println ("\n" + e);
            } // catch IOException
        } // while
        try
        {            
            maze = new Maze (fileScanner);
            System.out.println (INITIAL_STATE + maze);            
            if (!maze.isOK (maze.getStart()))
                System.out.println (START_INVALID);
            else if (!maze.isOK (maze.getFinish()))
                System.out.println (FINISH_INVALID);
            else
            {
                if (searchMaze (maze))                
                    System.out.println (SUCCESS);
                else
                    System.out.println (FAILURE);                    
                System.out.println (FINAL_STATE + maze);                                
            } // else valid search
        } // try
        catch (InputMismatchException e)
        {
            System.out.println ("\n" + e + ": " + fileScanner.nextLine());
        } // catch InputMismatchException  
        catch (NumberFormatException e)
        {
            System.out.println ("\n" + e);
        } // catch NumberFormatException
        catch (RuntimeException e)
        {
            System.out.println ("\n" + e);
            System.out.println (FINAL_STATE + maze);
        } // catch NumberFormatException
    } // method run


    /**
     * Performs the maze search.
     * 
     * @param maze the maze to be searched.   
     * 
     * @return true - if a path from start to finish was found; otherwise, false
     */
    public boolean searchMaze (Maze maze)
    {        
        Position start = maze.getStart();
        maze.markAsPossible (start);
        BackTrack backTrack = new BackTrack (maze);
        if (maze.isGoal (start) || backTrack.tryToReachGoal (start))
            return true;
        maze.markAsDeadEnd (start);
        return false;        
    } // method searchMaze

} // class MazeUser
