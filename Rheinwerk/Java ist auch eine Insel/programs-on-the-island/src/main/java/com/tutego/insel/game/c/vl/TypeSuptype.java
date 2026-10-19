package com.tutego.insel.game.c.vl;

public class TypeSuptype {
  public static void main( String[] args ) {
    Nap    napAsNap    = new Nap();
    Event  napAsEvent  = new Nap();
    Object napAsObject = new Nap();

    Workout workoutAsWorkout = new Workout();
    Event   workoutAsEvent   = new Workout();
    Object  workoutAsObject  = new Workout();

    //    System.out.println( workoutAsEvent.about );
    //    System.out.println( workoutAsEvent.caloriesBurned );
    //    // Cannot resolve symbol 'caloriesBurned'

    //    System.out.println( workoutAsObject.about );

    //    Event    event   = new Workout();    // Workout ist Objekttyp zur Laufzeit
    //    Workout  running = event;            //

    //    Event    event   = new Workout();
    //    Workout  running = (Workout) event;
    //    System.out.println( running.caloriesBurned );

    Event    event   = new Nap();
    Workout  running = (Workout) event;
    System.out.println( running.caloriesBurned );
  }
}
