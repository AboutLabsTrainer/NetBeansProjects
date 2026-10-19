public class ZZZZZnake {

  @SuppressWarnings( "resource" )
  public static void main( String[] args ) {
    java.awt.Point playerPosition = new java.awt.Point( 10, 9 );
    java.awt.Point snakePosition = new java.awt.Point( 30, 2 );
    java.awt.Point goldPosition = new java.awt.Point( 6, 6 );
    java.awt.Point doorPosition = new java.awt.Point( 0, 5 );
    boolean rich = false;

    while ( true ) {
      // Draw grid and symbols

      for ( int y = 0; y < 10; y++ ) {
        for ( int x = 0; x < 40; x++ ) {
          java.awt.Point p = new java.awt.Point( x, y );
          if ( playerPosition.equals( p ) )
            System.out.print( '&' );
          else if ( snakePosition.equals( p ) )
            System.out.print( 'S' );
          else if ( goldPosition.equals( p ) )
            System.out.print( '$' );
          else if ( doorPosition.equals( p ) )
            System.out.print( '#' );
          else System.out.print( '.' );
        }
        System.out.println();
      }

      // Determine status

      if ( rich && playerPosition.equals( doorPosition ) ) {
        System.out.println( "You won!" );
        return;
      }
      if ( playerPosition.equals( snakePosition ) ) {
        System.out.println( "SSSSSS. You were bitten by the snake!" );
        return;
      }
      if ( playerPosition.equals( goldPosition ) ) {
        rich = true;
        goldPosition.setLocation( -1, -1 );
      }

      // Console input and change player position
      // Keep playing field between 0/0.. 39/9
      switch ( new java.util.Scanner( System.in ).next() ) {
        case "u" /* p */   -> playerPosition.y = Math.max( 0, playerPosition.y - 1 );
        case "d" /* own */ -> playerPosition.y = Math.min( 9, playerPosition.y + 1 );
        case "l" /* eft */ -> playerPosition.x = Math.max( 0, playerPosition.x - 1 );
        case "r" /* ight */-> playerPosition.x = Math.min( 39, playerPosition.x + 1 );
      }

      // Snake moves towards the player

      if ( playerPosition.x < snakePosition.x )
        snakePosition.x--;
      else if ( playerPosition.x > snakePosition.x )
        snakePosition.x++;
      if ( playerPosition.y < snakePosition.y )
        snakePosition.y--;
      else if ( playerPosition.y > snakePosition.y )
        snakePosition.y++;
    } // end while
  }
}