package com.tutego.insel.sealed;

import com.tutego.insel.sealed.GeoJSONshape.Coordinate;

public class GeoJSONdemo {
  public static void main( String[] args ) {
    GeoJSONpoint point = new GeoJSONpoint( new Coordinate( 10, 20 ) );
    GeoJSONline poly = new GeoJSONline( new Coordinate( 20, 30 ),
                                        new Coordinate( 50, 90 ) );
    GeoJSONwriter.printGeoJSONcoordinates( point );
    GeoJSONwriter.printGeoJSONcoordinates( poly );
  }
}
