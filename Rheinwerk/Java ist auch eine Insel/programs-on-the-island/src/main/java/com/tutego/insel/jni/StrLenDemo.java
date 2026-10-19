package com.tutego.insel.jni;

public class StrLenDemo {
  public static void main( String[] args ) {
    System.out.println( "Vor JNI" );
    System.out.println( StrLen.strlen( "2003 UB313" ) );
    System.out.println( "Nach JNI" );
    // System.out.println( StrLen.strlen( null ) );
  }
}