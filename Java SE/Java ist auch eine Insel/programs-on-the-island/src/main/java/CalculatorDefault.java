import java.util.Scanner;

public class CalculatorDefault {

  public static void main( String[] args ) {
    double x        = new java.util.Scanner( System.in ).nextDouble();
    String operator = new Scanner( System.in ).nextLine();
    double y        = new java.util.Scanner( System.in ).nextDouble();

    switch ( operator ) {
      case "+": System.out.println( x + y ); break;
      case "-": System.out.println( x - y ); break;
      case "*": System.out.println( x * y ); break;
      case "/": System.out.println( x / y ); break;
      default : System.err.println( "Unknown operator " + operator );
    }
  }
}