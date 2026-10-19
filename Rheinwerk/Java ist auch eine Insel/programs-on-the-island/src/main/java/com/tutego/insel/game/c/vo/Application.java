package com.tutego.insel.game.c.vo;

public class Application {
  public static void main( String[] args ) {
    Event flight = new Event();
    flight.duration = 6 /* h */ * 60 + 12 /* min */;
    System.out.println( flight.format() ); // PT6H12M

    Event schalkeBvb = new SportsEvent();
    schalkeBvb.duration = 90;
    System.out.println( schalkeBvb.format() ); // 90 minutes
  }
}
