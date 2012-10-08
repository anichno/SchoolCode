
import java.util.ArrayList;


/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Jan 20, 2012 at 9:22:13 AM
 */
public class lesson7 {

  public static void main(String[] args) {
    ArrayList<String> team1 = new ArrayList();
    ArrayList<String> team2 = new ArrayList();
    ArrayList<ArrayList> league = new ArrayList();
    
    team1.add("Mark");
    team1.add("Joe");
    team1.add("Steve");
    
    team2.add("John");
    team2.add("Luke");
    team2.add("Mike");
    team2.add("Paul");
    
    league.add(team1);
    league.add(team2);
    
    System.out.print("Team 1: ");
    System.out.println(team1);
    System.out.print("Team 2: ");
    System.out.println(team2);
    System.out.print("League: ");
    System.out.println(league);
  }
}
