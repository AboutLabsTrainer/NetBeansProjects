package com.tutego.insel.generic;

public class GenericMethods {
  
  public static <T> T random( T m, T n ) {
    return Math.random() > 0.5 ? m : n;
  }
  
  public static void main( String[] args ) {
    String s = random( "Woo-Hoo!", "D'Oh!" );
    System.out.println( s );
  }
}