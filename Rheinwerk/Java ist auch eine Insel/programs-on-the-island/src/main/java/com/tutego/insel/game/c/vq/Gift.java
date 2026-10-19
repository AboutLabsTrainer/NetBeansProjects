package com.tutego.insel.game.c.vq;

import java.util.concurrent.ThreadLocalRandom;

class Gift extends Event {
  @Override void process( Player player ) {
    System.out.println( "Lucky you! Someone gives you sweets." );
    player.candy.quantity += ThreadLocalRandom.current().nextInt( 1, 10 );
  }
}
