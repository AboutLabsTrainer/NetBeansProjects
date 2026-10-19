public class WhatsYourNumber {

  @SuppressWarnings( "resource" )
  public static void main( String[] args ) {
    int number = (int) (Math.random() * 5 + 1);

    System.out.println( "What number between 1 and 5 do I have in mind?" );
    int guess = new java.util.Scanner( System.in ).nextInt();

    if ( number == guess ) {
      System.out.println( "Good guess!" );
    }

    System.out.println( "Restart the program and guess again!" );
  }
}