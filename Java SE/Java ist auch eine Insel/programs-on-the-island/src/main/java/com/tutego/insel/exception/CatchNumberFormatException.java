package com.tutego.insel.exception;

public class CatchNumberFormatException {

  public static void main( String[] args ) {
    
    String stringToConvert = "19%";
    int vat = 19;

    try {
      vat = Integer.parseInt( stringToConvert );
    } catch ( NumberFormatException e ) {
      System.err.printf( "'%s' cannot be converted to a number!%n", stringToConvert );
    }

    System.out.printf( "Moving on to VAT=%d%n", vat );
  }
}