package com.tutego.insel.oop;
interface Sex {
  default boolean hasPrice() {
    return false;
  }
}

interface Drugs {
  default boolean hasPrice() {
    return true;
  }
}

public class RockAndRoll implements Sex, Drugs {
  @Override
  public boolean hasPrice() {
    return Sex.super.hasPrice();
  }
}
