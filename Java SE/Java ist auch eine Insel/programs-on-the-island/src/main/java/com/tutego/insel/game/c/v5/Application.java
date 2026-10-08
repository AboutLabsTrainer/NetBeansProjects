package com.tutego.insel.game.c.v5;

public class Application {
  public static void main( String[] args ) {
    Candy candy = new Candy().name( "Nerds Candy" ).price( 80 );
    System.out.printf( "%s %d%n", candy.name(), candy.price() ); // Nerds Candy 80
  }
}
