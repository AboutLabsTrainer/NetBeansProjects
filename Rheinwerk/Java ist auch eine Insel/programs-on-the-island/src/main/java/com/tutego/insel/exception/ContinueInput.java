package com.tutego.insel.exception;

public class ContinueInput {

  public static void main( String[] args ) {
    int number;
    while ( true ) {
      try {
        System.out.println( "Please enter a number:" );
        String input = new java.util.Scanner( System.in ).nextLine();
        number = Integer.parseInt( input );
        break;
      }
      catch ( NumberFormatException e ) {
        System.err.println( "That was not a number!" );
      }
    }
    System.out.printf( "%d is a %s number%n", number,
                       Math.random() > 0.5 ? "wonderful" : "boring" );
  }
}