package com.tutego.insel.enums.v3;

public class CountryEnumDemo {

  public static void main( String[] args ) {
    System.out.println( Country.GERMANY.getISO3Country() );
    System.out.println( Country.CHINA.getISO3Country() );
    System.out.println( Country.UK.getISO3Country() );

    Country c = Country.GERMANY;

    switch ( c ) {
      case GERMANY -> {
        System.out.println( "A beer?" );
        System.out.println( c.getISO3Country() ); // DEU
      }
      default -> System.out.println( "Different country" );
    }
  }
}