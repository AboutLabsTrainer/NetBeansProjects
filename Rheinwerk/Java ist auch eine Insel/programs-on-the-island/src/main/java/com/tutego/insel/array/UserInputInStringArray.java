package com.tutego.insel.array;

import java.util.Scanner;

public class UserInputInStringArray {

  @SuppressWarnings( "resource" )
  public static void main( String[] args ) {
    String[] validInputs = { "Banana", "Apple", "Cherry" };

    String input = null;
    boolean found = false;
    while ( ! found ) {
      input = new Scanner( System.in ).nextLine();

      for ( String s : validInputs )
        if ( s.equalsIgnoreCase( input ) ) {
          found = true;
          break;
        }
    }

    System.out.println( "Yummy " + input );
  }
}