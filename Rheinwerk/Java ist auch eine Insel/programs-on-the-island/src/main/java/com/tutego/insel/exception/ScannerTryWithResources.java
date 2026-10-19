package com.tutego.insel.exception;

import java.io.InputStream;
import java.util.Scanner;

public class ScannerTryWithResources {

  public static void main( String[] args ) {
    InputStream in = ClassLoader.getSystemResourceAsStream( "EastOfJava.txt" );
    try ( Scanner res = new Scanner( in ) ) {
      System.out.println( res.nextLine() );
    }
  }
}