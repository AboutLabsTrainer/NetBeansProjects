package com.tutego.insel.game.c.vi;

class Application {
  public static void main( String[] args ) {
    City candyTown  = new City( "Candy Town" );
    Player pillLady = new Player();
    pillLady.travelTo( candyTown );
    System.out.println( pillLady.currentLocation() ); // Candy Town
    pillLady.travelTo( new City( "Riesen City" ) );
    System.out.println( pillLady.currentLocation() ); // Riesen City
  }
}
