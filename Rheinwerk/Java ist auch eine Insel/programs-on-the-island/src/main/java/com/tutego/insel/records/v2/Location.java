package com.tutego.insel.records.v2;

import java.awt.geom.Point2D;

public record Location(
    double latitude,
    double longitude
) {

  Point2D.Double toPoint() {
    return new Point2D.Double( longitude, latitude );
  }

  Location withLatitude( double latitude ) {
    return new Location( longitude, latitude );
  }

  Location withLongitude( double longitude ) {
    return new Location( longitude, latitude );
  }

  @Override public double longitude() {
    System.out.println( "Access" + longitude );
    return longitude;
  }

  @Override public String toString() {
    return latitude + "," + longitude;
  }

  static boolean isValid( double latitude, double longitude ) {
    return    ( -90 <= latitude  && latitude  <= +90)
           && (-180 <= longitude && longitude <= +180);
  }
}
