package com.tutego.insel.game.c.vn;

class Workout extends Event {
  int caloriesBurned;

  @Override public String toString() {
    return String.format( "%s[caloriesBurned=%d]",
                          super.toString(),
                          caloriesBurned );
  }
}
