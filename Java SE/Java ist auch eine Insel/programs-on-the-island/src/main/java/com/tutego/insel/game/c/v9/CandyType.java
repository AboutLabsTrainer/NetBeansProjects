package com.tutego.insel.game.c.v9;

public class CandyType {
  private CandyType() { }

  public static final int OTHER     = 0;
  public static final int CARAMELS  = 1;
  public static final int CHOCOLATE = CARAMELS + 1;
  public static final int GUMMIES   = CHOCOLATE * 2;
}
