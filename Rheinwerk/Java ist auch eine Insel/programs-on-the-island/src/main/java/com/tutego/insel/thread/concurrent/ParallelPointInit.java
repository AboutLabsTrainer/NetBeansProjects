package com.tutego.insel.thread.concurrent;

import java.awt.Point;

public class ParallelPointInit {

  public static void main( String[] args ) {
    Point p = new Point();

    Runnable r = () -> {
      int rnd = (int) (Math.random() * 1000);
  
      while ( true ) {
        p.x = p.y = rnd;           // *

        int xc = p.x, yc = p.y;    // *

        if ( xc != yc )
          System.out.println( "Aha: x=" + xc + ", y=" + yc );
      }
    };

    new Thread( r ).start();
    new Thread( r ).start();
  }
}