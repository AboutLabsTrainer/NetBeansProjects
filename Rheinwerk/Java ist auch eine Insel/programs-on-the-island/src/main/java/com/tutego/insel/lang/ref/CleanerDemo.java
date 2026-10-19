package com.tutego.insel.lang.ref;

import java.awt.Point;
import java.lang.ref.Cleaner;

public class CleanerDemo {
  public static void main( String[] args ) {

    Point p = new Point( 1, 2 );
    Cleaner.create().register( p, () -> System.out.println( "point is gone" ) );
    p = null;

    new byte[ (int) Runtime.getRuntime().freeMemory() ].clone();
  }
}
