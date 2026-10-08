package com.tutego.insel.game.c.vq;

import java.util.Scanner;

public class Application {
  public static void main( String[] args ) {
    Candy liquorice = new Candy();
    liquorice.quantity = 10;
    liquorice.name = "Leckmuschel";
    Player peter = new Player();
    peter.candy = liquorice;
    peter.location = "Dortmund";

    System.out.println( "Enter the destination where you want to travel. "
                      + "An empty input will end the game." );
    while ( true ) {
      System.out.printf( "You are currently in %s and hold %d '%s'%n",
                         peter.location, peter.candy.quantity, peter.candy.name );
      System.out.print( "Destination: " );
      String input = new Scanner( System.in ).nextLine();
      if ( input.trim().isEmpty() )
        break;
      peter.moveTo( input );
    }
  }
}