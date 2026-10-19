package com.tutego.insel.records.v2;

public class LocationDemo {
  public static void main( String[] args ) {
    Location manila = new Location( 14.60416, 120.98222 );
    System.out.println( manila.toPoint() );
    System.out.println( manila.withLatitude( 14 ) );
    System.out.println( manila.withLongitude( 120 ) );
    System.out.println( manila.longitude() );
    System.out.println( manila );
    System.out.println( Location.isValid( 15, 120 ) );
    System.out.println( Location.isValid( 200, 0 ) );
  }
}
