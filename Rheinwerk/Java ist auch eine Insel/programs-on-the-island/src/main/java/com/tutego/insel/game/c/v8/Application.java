package com.tutego.insel.game.c.v8;

public class Application {
  public static void main( String[] args ) {
    System.out.println( City.KANDY_HERSHEY_DISTANCE );             // 124
    City.KANDY_HERSHEY_DISTANCE = 120;

    System.out.println( City.KANDY_HERSHEY_DISTANCE );             // 120
    System.out.println( City.distance( "Kandy", "Hershey" ) );     // 120
    System.out.println( City.distance( "Neijiangs", "hershey" ) ); // -1
    System.out.println( City.distance( "kandy", "kandy" ) );       // 0
  }
}
