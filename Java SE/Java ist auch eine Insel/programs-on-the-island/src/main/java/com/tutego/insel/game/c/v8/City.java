package com.tutego.insel.game.c.v8;

public class City {

  public static int KANDY_HERSHEY_DISTANCE = 124;

  public String name;

  public static int distance( String cityName1, String cityName2 ) {
    if ( cityName1.equalsIgnoreCase( cityName2 )) return 0;
    if ( (cityName1.equalsIgnoreCase( "kandy" ) &&
          cityName2.equalsIgnoreCase( "hershey" )) ||
         (cityName1.equalsIgnoreCase( "hershey" ) &&
          cityName2.equalsIgnoreCase( "kandy" )) )
      return KANDY_HERSHEY_DISTANCE;
    return -1;
  }
}
