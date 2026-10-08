package com.tutego.insel.game.c.vq;

class Mugging extends Event {
  @Override void process( Player player ) {
    System.out.println( "Damn it! Someone steals all your sweets." );
    player.candy.quantity = 0;
  }
}
