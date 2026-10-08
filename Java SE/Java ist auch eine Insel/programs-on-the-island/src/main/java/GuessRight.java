public class GuessRight {

  @SuppressWarnings( "resource" )
  public static void main( String[] args ) {
    int number = (int) (Math.random() * 5 + 1);

    while ( true ) {
      System.out.println( "What number between 1 and 5 do I have in mind?" );
      int guess = new java.util.Scanner( System.in ).nextInt();

      if ( guess < 1 || guess > 5 ) {
        System.out.println( "Only numbers between 1 and 5!" );
        continue;
      }

      if ( number == guess ) {
        System.out.println( "Good guess!" );
        break;                   // End of loop
      }
      else if ( number > guess )
        System.out.println( "Nope, my number is larger than yours!" );
      else if ( number < guess )
        System.out.println( "Nope, my number is larger than yours!" );
    }
  }
}