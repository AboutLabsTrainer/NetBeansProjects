package com.tutego.insel.game.c.va;

import java.util.Objects;

public class Candy {

  private CandyType type;

  public void setType( CandyType type ) {
    this.type = Objects.requireNonNull( type, "CandyType can’t be null" );
  }

  public CandyType getCandyType() {
    return type;
  }
}
