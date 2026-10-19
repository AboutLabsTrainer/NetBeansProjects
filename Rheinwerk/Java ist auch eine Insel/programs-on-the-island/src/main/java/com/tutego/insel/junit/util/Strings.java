package com.tutego.insel.junit.util;

public class Strings {

  /**
   * @param string input.
   * @return reversed string
   */
  public static String reverse( String string ) {
    if ( string == null )
      throw new IllegalArgumentException( "The string to reversed is not allowed to be null" );

    return new StringBuilder( string ).reverse().toString();
  }
}
