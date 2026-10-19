package com.tutego.insel.game.c.vq;

class Eating extends Event {
  @Override void process( Player player ) {
    System.out.println( "You are hungry and you eat some of your sweets." );
    player.candy.quantity *= Math.random();
  }
}
