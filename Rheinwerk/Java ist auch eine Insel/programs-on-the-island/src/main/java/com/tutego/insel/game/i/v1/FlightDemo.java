package com.tutego.insel.game.i.v1;

public class FlightDemo {
  public static void main( String[] args ) {
    Flight londonToDurban = new Flight( 1200 );
    System.out.println( londonToDurban instanceof Flight );     // true
    System.out.println( londonToDurban instanceof Event );      // true
    System.out.println( londonToDurban instanceof Object );     // true
    System.out.println( londonToDurban instanceof Buyable );    // true
    System.out.println( londonToDurban instanceof Comparable ); // true
  }
}
