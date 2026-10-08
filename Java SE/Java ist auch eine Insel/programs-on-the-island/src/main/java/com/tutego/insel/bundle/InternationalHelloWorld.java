package com.tutego.insel.bundle;

import java.util.*;

//java -Duser.language=en InternationalHelloWorld

public class InternationalHelloWorld {

  public static void main( String[] args ) {
//    Locale locale = new Locale( "de", "CH" );
//    Locale locale = new Locale( "de" );
    Locale locale = Locale.ENGLISH;

    String baseName = "bundles.HelloWorld";

    try {
      ResourceBundle bundle = ResourceBundle.getBundle( baseName, locale );
      System.out.println( bundle.getString( "Hello" ) );
      System.out.printf( bundle.getString( "Bye" ), "Christian" );
    }
    catch ( MissingResourceException e ) {
      e.printStackTrace();
    }
  }
}
