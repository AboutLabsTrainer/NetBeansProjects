package com.tutego.insel.game.c.vm;

public class Workout extends Event {
  int caloriesBurned;

  @Override public String toString() {
    return String.format( "%s[about=%s, duration=%d, caloriesBurned=%d]",
                          getClass().getSimpleName(),
                          about, duration, caloriesBurned );
  }
}
