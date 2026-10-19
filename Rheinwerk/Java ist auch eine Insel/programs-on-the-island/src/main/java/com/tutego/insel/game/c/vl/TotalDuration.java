package com.tutego.insel.game.c.vl;

public class TotalDuration {
  public static int totalDuration( Event... events ) {
    int sum = 0;
    for ( Event event : events )
      sum += event.duration;
    return sum;
  }

  public static void main( String[] args ) {
    Workout running = new Workout();
    running.duration = 50;
    Event sleeping = new Nap();
    sleeping.duration = 40;
    System.out.println( totalDuration( running, sleeping ) );
  }
}
