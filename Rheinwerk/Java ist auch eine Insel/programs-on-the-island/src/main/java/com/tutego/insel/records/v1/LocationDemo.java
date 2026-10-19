package com.tutego.insel.records.v1;

public class LocationDemo {
  public static void main( String[] args ) {
    Location manila = new Location( 14.60416, 120.98222 );
    System.out.printf( "latitude=%f, longitude=%f%n",
                       manila.latitude(), manila.longitude() );
    System.out.println( manila );

    Location location1 = new Location( 14.60416, 120.98222 );
    System.out.println( location1.equals( manila ) );
    Location location2 = new Location( 14, 120 );
    System.out.println( location2.equals( manila ) );
  }
}
