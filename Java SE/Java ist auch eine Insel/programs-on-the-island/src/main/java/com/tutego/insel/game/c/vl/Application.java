package com.tutego.insel.game.c.vl;

public class Application {
  public static void main( String[] args ) {
    Workout walking = new Workout();
    walking.about = "Go for a run";      // Zugriff auf geerbte Objektvariable
    walking.duration = 30;               // Zugriff auf geerbte Objektvariable
    walking.caloriesBurned = 200;        // Zugriff auf eigene Objektvariable

    Nap sleeping = new Nap();
    sleeping.about = "Recreational sleep"; // Zugriff auf geerbte Objektvariable
    sleeping.duration = 60;                // Zugriff auf geerbte Objektvariable
  }
}
