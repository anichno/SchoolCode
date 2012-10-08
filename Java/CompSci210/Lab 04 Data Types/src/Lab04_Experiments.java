
/**
 * Experiments with data types, variables, and expressions
 * 
 * @author Wayne.Brown
 * 
 * @version 1.0 Aug 9, 2011
 */
public class Lab04_Experiments {

  //-------------------------------------------------------------------
  public static void main(String[] args) {
    //Experiment1();
    //Experiment2();
    //Experiment3();
    //Experiment4();
    //Experiment5();
    //Experiment6();
    Experiment7();
  }

  //-------------------------------------------------------------------
  // Demonstrate the declaration and initialization of the basic data types
  public static void Experiment1() {

    byte alpha = 23;
    short beta = 45;
    int gamma = 12;
    long theta = 62;
    float epsilon = (float) 3.4;
    double omega = 5.78;
    char letter = '$';
    boolean logic = true;

    System.out.println("byte    variable alpha   holds a " + alpha);
    System.out.println("short   variable beta    holds a " + beta);
    System.out.println("int     variable gamma   holds a " + gamma);
    System.out.println("long    variable theta   holds a " + theta);
    System.out.println("float   variable epsilon holds a " + epsilon);
    System.out.println("double  variable omega   holds a " + omega);
    System.out.println("char    variable letter  holds a " + letter);
    System.out.println("boolean variable logic   holds a " + logic);
  }

  //-------------------------------------------------------------------
  // Demonstrate operator precedence
  public static void Experiment2() {

    int alpha = 2;
    int beta = 3;
    int gamma = 4;
    int delta = 5;

    int answer1 = alpha + beta * gamma - delta;
    int answer2 = (alpha + beta) * gamma - delta;
    int answer3 = alpha + beta * (gamma - delta);
    int answer4 = (alpha + beta) * (gamma - delta);
    System.out.println("answer 1 = " + answer1);
    System.out.println("answer 2 = " + answer2);
    System.out.println("answer 3 = " + answer3);
    System.out.println("answer 4 = " + answer4);
  }

  //-------------------------------------------------------------------
  // Practice casting values to different data types
  public static void Experiment3() {

    byte alpha = 2;
    short beta = 3;
    int gamma = 4;
    long delta = 5;


    long answer1 = alpha + beta * gamma - delta;
    long answer2 = alpha + beta * gamma - delta;
    long answer3 = alpha + beta * gamma - delta;
    long answer4 = alpha + beta * gamma - delta;

    System.out.println("answer 1 = " + answer1);
    System.out.println("answer 2 = " + answer2);
    System.out.println("answer 3 = " + answer3);
    System.out.println("answer 4 = " + answer4);

  }

  //-------------------------------------------------------------------
  // Practice casting values to appropriate data types
  public static void Experiment4() {

    float alpha = (float) 2.3;
    double beta = 3.0;


    double answer1 = alpha * beta;
    double answer2 = alpha * beta;
    double answer3 = alpha * beta;

    System.out.println("answer 1 = " + answer1);
    System.out.println("answer 2 = " + answer2);
    System.out.println("answer 3 = " + answer3);

  }

  //-------------------------------------------------------------------
  // Practice casting values to appropriate data types
  public static void Experiment5() {
    char alpha = '$';
    char beta = '1';
    char gamma = 'a';

    char answer1 = (char) ((int) alpha + 1);
    char answer2 = (char) ((int) beta + 5);
    char answer3 = (char) ((int) gamma - 32);

    System.out.println("answer 1 = " + answer1);
    System.out.println("answer 2 = " + answer2);
    System.out.println("answer 3 = " + answer3);
  }

  //-------------------------------------------------------------------
  // All data types have limited precision
  public static void Experiment6() {

    byte alpha = +127;
    byte beta = -128;

    byte answer1 = (byte) (alpha - 3);
    byte answer2 = (byte) (alpha + 1);
    byte answer3 = (byte) (alpha + 100);

    byte answer4 = (byte) (beta - 3);
    byte answer5 = (byte) (beta + 1);
    byte answer6 = (byte) (beta - 100);

    System.out.println("answer1 = " + answer1);
    System.out.println("answer2 = " + answer2);
    System.out.println("answer3 = " + answer3);
    System.out.println("answer4 = " + answer4);
    System.out.println("answer5 = " + answer5);
    System.out.println("answer6 = " + answer6);
  }

  //-------------------------------------------------------------------
  // All data types have limited precision
  public static void Experiment7() {
    float alpha = (float) 2147483647.0;

    float answer1 = alpha;
    float answer2 = alpha + 129;
    float answer3 = alpha + 129;

    System.out.printf("answer1 = %10.0f\n", answer1);
    System.out.printf("answer2 = %10.0f\n", answer2);
    System.out.printf("answer3 = %10.0f\n", answer3);
  }
}
