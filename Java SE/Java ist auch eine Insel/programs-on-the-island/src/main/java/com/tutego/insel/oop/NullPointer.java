package com.tutego.insel.oop;                    // 1
public class NullPointer {                       // 2
  public static void main( String[] args ) {     // 3
    java.awt.Point p = null;                     // 4
    String         s = null;                     // 5
    p.setLocation( 1, 2 );                       // 6
    s.length();                                  // 7
  }                                              // 8
}                                                // 9
