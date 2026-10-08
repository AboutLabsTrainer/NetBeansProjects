package com.tutego.insel.enums.v1;

import java.util.Arrays;

import static com.tutego.insel.enums.v1.Country.valueOf;

public class CountryDemo {

  public static void main( String[] args ) {
    System.out.println( Country.GERMANY.toString() );            // GERMANY
    System.out.println( Country.GERMANY.name() );                // GERMANY

    Country germany1 = valueOf( "GERMANY" );
    Country germany2 = Enum.valueOf( Country.class, "GERMANY" );
    System.out.println( germany1 );   // GERMANY
    System.out.println( germany2 );   // GERMANY

    for ( Country d : Country.values() )
      System.out.printf( "%s ", d );
    System.out.printf( "%n%s%n",
                       Arrays.asList( Country.class.getEnumConstants() ) );

    //

    System.out.println( getOrdinal( "UK" ) );
    System.out.println( getOrdinal( "ENGLAND" ) );

    Class<Country> declaringClass = Country.UK.getDeclaringClass();

    String name = "GERMANY";
    Class<Country> enumClass = Country.class;
    Enum<Country>  anEnum = Enum.valueOf( enumClass, name );
    System.out.println( anEnum );
  }

  static int getOrdinal( String name ) {
    try {
      return valueOf( name ).ordinal();
    }
    catch ( IllegalArgumentException e ) {
      return -1;
    }
  }
}