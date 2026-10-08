package com.tutego.insel.records.v3;

public class LocationDemo {
  public static void main( String[] args ) {

    System.out.println( new Location( 14.60416, 120.98222 ) );
    try {
      System.out.println( new Location( -1000, +1000 ) );
    }
    catch ( Exception e ) {
      e.printStackTrace();
    }
  }
}
