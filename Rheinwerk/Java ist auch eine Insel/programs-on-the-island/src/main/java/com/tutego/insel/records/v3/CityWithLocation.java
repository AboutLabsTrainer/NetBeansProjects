package com.tutego.insel.records.v3;

public class CityWithLocation {
  public static void main( String[] args ) {
    City city = new City( "Gothic", 234534, 8374, new Location( 14, 120 ) );
    System.out.println( city.center() );//Location[latitude=14.0, longitude=120.0]
  }
}
