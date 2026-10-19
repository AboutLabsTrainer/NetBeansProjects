package com.tutego.insel.game.c.vd;

public class Candy {
  public String name;
  public int    price;

  public Candy() {
  }

  public Candy( Candy other ) {
    this.name = other.name;
    this.price = other.price;
  }
}
