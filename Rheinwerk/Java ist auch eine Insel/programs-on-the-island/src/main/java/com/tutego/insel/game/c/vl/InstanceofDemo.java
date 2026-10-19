package com.tutego.insel.game.c.vl;

public class InstanceofDemo {
  public static void main( String[] args ) {
    Workout wo1 = new Workout();
    System.out.println( wo1 instanceof Workout );    // true
    System.out.println( wo1 instanceof Event );      // true
    System.out.println( wo1 instanceof Object );     // true

    Event wo2 = new Workout();
    System.out.println( wo2 instanceof Workout );    // true
    System.out.println( wo2 instanceof Event );      // true
    System.out.println( wo2 instanceof Object );     // true
    System.out.println( wo2 instanceof Nap );        // false

    Object wo3 = new Workout();
    System.out.println( wo3 instanceof Workout );    // true
    System.out.println( wo3 instanceof Event );      // true
    System.out.println( wo3 instanceof Object );     // true
    System.out.println( wo3 instanceof Nap );        // false
    System.out.println( wo3 instanceof String );     // false
  }
}
