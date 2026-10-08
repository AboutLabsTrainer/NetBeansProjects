package com.tutego.insel.array;

import java.util.Arrays;

public class CandyNamesArraysToString {

  public static void main( String[] args ) {
    String[] names = {
        "Butterfinger", "Dickmanns", "Fruitella", "Nutbar"
    };

    System.out.println( names );                    // [Ljava.lang.String;@21b8d17c
    System.out.println( Arrays.toString( names ) ); // [Butterfinger, Dickmanns, ...]
  }
}
