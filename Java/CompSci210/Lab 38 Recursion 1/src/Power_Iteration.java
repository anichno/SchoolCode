/**
 * Description: Raise an integer to an integer power.
 *
 * @author  Dr. Brown
 *
 * @version 1.0   Fall 2008
 *
 */

public class Power_Iteration
{
  //-------------------------------------------------------------------
  public static void main(String[] args)
  {
    System.out.println( "2^5 = " + power(2,5) );
    System.out.println( "10^3 = " + power(10,3) );
    System.out.println( "3^12 = " + power(3,12) );
    System.out.println( "5^5 = " + power(5,5) );
  }
  
  //-------------------------------------------------------------------
  // Precondition: exponent must be non-negative
  public static int power(int base, int exponent)
  {    
    int answer = 1;
    for (int j=0; j<exponent; j++)
      answer *= base;
      
    return answer;
  }  
}

