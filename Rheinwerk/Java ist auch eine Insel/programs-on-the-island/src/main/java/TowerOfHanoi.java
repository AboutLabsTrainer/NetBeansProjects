class TowerOfHanoi {

  static void move( int n, String fromPeg, String toPeg, String usingPeg ) {
    if ( n > 1 ) {
      move( n - 1, fromPeg, usingPeg, toPeg );
      System.out.printf( "Move disk %d from %s to %s.%n", n, fromPeg, toPeg );
      move( n - 1, usingPeg, toPeg, fromPeg );
    }
    else
      System.out.printf( "Move disk %d from %s to %s.%n", n, fromPeg, toPeg );
  }

  public static void main( String[] args ) {
    move( 4, "copper peg", "gold peg", "silver peg" );
  }
}
