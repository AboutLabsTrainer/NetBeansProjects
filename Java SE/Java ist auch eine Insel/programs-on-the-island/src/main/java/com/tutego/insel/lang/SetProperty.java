package com.tutego.insel.lang;

import static java.util.Optional.ofNullable;
import java.util.Optional;

class SetProperty {
  static public void main( String[] args ) {
    Optional<String> logProperty = ofNullable( System.getProperty( "LOG" ) );
    Optional<String> usernameProperty = ofNullable( System.getProperty( "USER" ) );
    Optional<String> sizeProperty = ofNullable( System.getProperty( "SIZE" ) );

    System.out.println( logProperty.isPresent() );                      // true
    usernameProperty.ifPresent( System.out::println );                  // Chris
    sizeProperty.map( Integer::parseInt ).ifPresent( System.out::println ); // 100
    System.out.println( System.getProperty( "DEBUG", "false" ) );       // false
  }
}