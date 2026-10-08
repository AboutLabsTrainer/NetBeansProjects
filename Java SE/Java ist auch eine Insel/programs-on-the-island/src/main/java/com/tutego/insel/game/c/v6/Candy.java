package com.tutego.insel.game.c.v6;

public class Candy {
  private String name;

  public void setName( String name ) {
    this.name = name;
  }

  public boolean hasSameName( Candy that ) {
    return this.name.equals( that.name );
  }
}
