package com.tutego.insel.records;

public class EventAndRecordImplementations {

  interface Event {
    String about();

    int duration();
  }

  record Nap( String about, int duration ) implements Event {}

  record Workout( String about, int duration, int caloriesBurned ) implements Event {}

  public static void main( String[] args ) {
    Event event = new Nap( "Snooze Olympics", 69 );
    System.out.println( event.about() );
    System.out.println( event.duration() );

    switch ( event ) {
      case Nap nap ->
          System.out.printf( "%d minutes of ninja-level rest!%n", nap.duration );
      case Workout( var about, var duration, var calories ) ->
          System.out.printf("You just burned %d calories for a guilt-free gummy bear.%n", calories );
      default -> {}
    }
  }
}
