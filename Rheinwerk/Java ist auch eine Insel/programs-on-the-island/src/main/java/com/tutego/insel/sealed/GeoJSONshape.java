package com.tutego.insel.sealed;

sealed interface GeoJSONshape
    permits GeoJSONpoint, GeoJSONline {
  record Coordinate(int x, int y) {
    @Override public String toString() { return '[' + x + "," + y + ']'; }
  }
}
