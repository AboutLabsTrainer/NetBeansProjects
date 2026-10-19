package com.tutego.insel.game.c.vi;

class Player {
  private City location;
  void travelTo( City newLocation ) {
    this.location = newLocation;
  }
  City currentLocation() {
    return location;
  }
}
