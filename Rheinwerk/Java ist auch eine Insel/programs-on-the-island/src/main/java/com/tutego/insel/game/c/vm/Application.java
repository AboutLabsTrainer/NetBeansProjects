package com.tutego.insel.game.c.vm;

public class Application {
  public static void main( String[] args ) {
    Event e = new Event();
    String s = e.toString();
    System.out.println( s ); // com.tutego.insel.game.c.vm.Event@1a2b3c4d

    Workout running = new Workout();
    running.about = "Go for a run";
    running.duration = 60;
    running.caloriesBurned = 250;
    System.out.println( running );
    // Workout[about=Go for a run, duration=60, caloriesBurned=250]
  }
}
