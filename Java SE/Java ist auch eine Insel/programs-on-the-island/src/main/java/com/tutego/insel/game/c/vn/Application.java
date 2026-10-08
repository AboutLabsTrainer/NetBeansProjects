package com.tutego.insel.game.c.vn;

public class Application {
  public static void main( String[] args ) {
    Workout running = new Workout();
    running.about = "Go for a run";
    running.duration = 60;
    running.caloriesBurned = 250;
    System.out.println( running );
    // Workout[about=Go for a run, duration=60][caloriesBurned=250]
  }
}
