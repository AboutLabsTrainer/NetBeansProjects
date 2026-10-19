package com.tutego.insel.game.c.vl;

public class NonNapTime {

  public static int totalDurationOfNoNapEvents( Event... events ) {
    int sum = 0;
    for ( Event event : events )
      if ( ! (event instanceof Nap) )
        sum += event.duration;
    return sum;
  }
}
