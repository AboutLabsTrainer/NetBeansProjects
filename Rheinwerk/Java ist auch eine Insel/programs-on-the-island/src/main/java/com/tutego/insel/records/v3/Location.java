package com.tutego.insel.records.v3;

public record Location(
    double latitude,
    double longitude
) {

  public Location {
    if ( ! isValid( latitude, longitude ) )
      throw new IllegalArgumentException( "Invalid range" );
  }

  private static boolean isValid( double latitude, double longitude ) {
    return    ( -90 <= latitude  && latitude  <= +90)
           && (-180 <= longitude && longitude <= +180);
  }
}
