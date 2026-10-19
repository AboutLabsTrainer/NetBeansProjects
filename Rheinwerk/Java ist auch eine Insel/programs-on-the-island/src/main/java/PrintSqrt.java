public class PrintSqrt {

  static void printSqrt( double d ) {
    if ( d < 0 ) {
      System.out.println( "There is no true square root of a negative number!" );
      return;
    }
    System.out.println( Math.sqrt( d ) );
  }
}
