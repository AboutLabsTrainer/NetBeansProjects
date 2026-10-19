package com.tutego.insel.game.c.v5;

class Candy {
  String name;
  int    price;

  public Candy name( String name ) {
    this.name = name;
    return this;
  }

  public String name() {
    return name;
  }

  public Candy price( int price ) {
    this.price = price;
    return this;
  }

  public int price() {
    return price;
  }
}
