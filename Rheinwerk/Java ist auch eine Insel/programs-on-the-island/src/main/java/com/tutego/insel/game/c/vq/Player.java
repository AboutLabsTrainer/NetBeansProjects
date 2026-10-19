package com.tutego.insel.game.c.vq;

class Player {
  Candy  candy;
  String location;

  void moveTo( String cityName ) {
    if ( location.equalsIgnoreCase( cityName ) ) {
      System.out.println( "You are already there! (◔_◔)" );
      return;
    }
    RandomGameEvents.next().process( this );
    location = cityName;
  }
}
