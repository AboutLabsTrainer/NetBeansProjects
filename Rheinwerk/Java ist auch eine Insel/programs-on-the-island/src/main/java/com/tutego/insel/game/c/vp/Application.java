package com.tutego.insel.game.c.vp;

public class Application {
  public static void main( String[] args ) {
    // Event flight = new Event(); // 'Event' is abstract; cannot be instantiated

    Event sleep = new Nap();
    Event running = new Workout();
    Event[] events = { new Nap(), new Nap(), new Workout(), new Nap() };
  }
}
