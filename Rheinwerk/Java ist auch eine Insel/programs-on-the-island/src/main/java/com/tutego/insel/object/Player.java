package com.tutego.insel.object;

public class Player implements Cloneable {
  
  public String name;
  public int    age;

  @Override
  public Player clone() {
    try {
      return (Player) super.clone();
    }
    catch ( CloneNotSupportedException e ) {
      // Kann eigentlich nicht passieren, da Cloneable
      throw new InternalError();
    }
  }
}