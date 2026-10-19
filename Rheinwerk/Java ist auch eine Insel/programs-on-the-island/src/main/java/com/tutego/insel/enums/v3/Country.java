package com.tutego.insel.enums.v3;

public enum Country {

  GERMANY, UK, CHINA;

  public String getISO3Country() {
    return switch ( this ) {
      case GERMANY -> "DEU";
      case UK -> "GBR";
      default -> "CHN";
    };
  }
}