class PointEqualsDemo {

  public static void main( String[] args ) {
    java.awt.Point player = new java.awt.Point();
    player.x = player.y = 10;

    java.awt.Point door = new java.awt.Point();
    door.setLocation( 10, 10 );

    System.out.println( player.equals( door ) );   // true
    System.out.println( door.equals( player ) );   // true

    java.awt.Point snake = new java.awt.Point();
    snake.setLocation( 20, 22 );

    System.out.println( snake.equals( door ) );   // false
  }
}
