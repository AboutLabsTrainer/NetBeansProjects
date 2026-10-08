package com.tutego.insel.game.c.vk;

import java.util.Objects;

public class Candy {
  public final String name;
  public final int calories;

  public Candy( String name, int calories ) {
    this.name     = Objects.requireNonNull( name );
    this.calories = calories;
  }

  @Override public String toString() { return name + ", " + calories; }
}
