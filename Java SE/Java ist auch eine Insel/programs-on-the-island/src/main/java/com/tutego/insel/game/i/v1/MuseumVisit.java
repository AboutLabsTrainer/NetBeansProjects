package com.tutego.insel.game.i.v1;

class MuseumVisit extends Event implements Buyable {
  int price;
  MuseumVisit( int price ) { this.price = price; }

  @Override public double price() {
    return price;
  }
}
