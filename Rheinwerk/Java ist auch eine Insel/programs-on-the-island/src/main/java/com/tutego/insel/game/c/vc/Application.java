package com.tutego.insel.game.c.vc;

public class Application {
  public static void main( String[] args ) {
    City kandy = new City( "Kandy" );
    System.out.printf( "%s %d%n",
                       kandy.getName(), kandy.getPopulation() );     // Kandy 0
    City hershey = new City( "Hershey", 10_200 );
    System.out.printf( "%s %d%n",
                       hershey.getName(), hershey.getPopulation() ); // Hershey 10200
  }
}
