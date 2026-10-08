package com.tutego.insel.records.v4;

import java.awt.*;

public record Location(
  double latitude,
  double longitude
) {

  public Location( double latitude, double longitude ) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public Location() {
    this( 0, 0 );
  }

  public Location( Point point ) {
    this( point.y, point.x );
    // this.latitude = point.y;
    // this.longitude = point.x;
  }

  public static Location fromPoint( Point point ) {
    return new Location( point.y, point.x );
  }
}
