package com.tutego.insel.game.c.vh;

public class Application {
  public static void main( String[] args ) {
    City almostKandy = new City( "H", 100 );
    City kandy = almostKandy.withPopulation( 10_200 ).withName( "Hershey" );
    System.out.printf( "%s %d", kandy.name, kandy.population ); // Hershey 10200
  }
}
