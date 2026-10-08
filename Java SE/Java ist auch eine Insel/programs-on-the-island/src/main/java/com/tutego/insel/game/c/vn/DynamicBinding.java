package com.tutego.insel.game.c.vn;

public class DynamicBinding {
  public static void main( String[] args ) {
    Workout ww = new Workout();
    ww.about = "Running";
    ww.duration = 100;
    ww.caloriesBurned = 300;
    System.out.println( ww.toString() );

    Event ew = new Workout();
    ew.about = "Running";
    ew.duration = 100;
    System.out.println( ew.toString() );

    Object ow = new Workout();
    System.out.println( ow.toString() );
  }
}
