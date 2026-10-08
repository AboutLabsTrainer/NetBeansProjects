package com.tutego.insel.enums.v4;

public enum Country {

  GERMANY( "DEU" ), UK( "GBR" ), CHINA( "CHN" );

  final String iso3Country;

  Country( String iso3Country ) {
    this.iso3Country = iso3Country;
  }

  public String getISO3Country() {
    return iso3Country;
  }
}