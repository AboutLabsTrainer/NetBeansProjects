package com.tutego.insel.game.i.v1;

public class Application {
  public static void main( String[] args ) {
    Buyable hercules = new Bike();
    Buyable binarium = new MuseumVisit( 8 );
    System.out.println( hercules.price() );  // 199.0
    System.out.println( binarium.price() );  // 8.0
  }
}
