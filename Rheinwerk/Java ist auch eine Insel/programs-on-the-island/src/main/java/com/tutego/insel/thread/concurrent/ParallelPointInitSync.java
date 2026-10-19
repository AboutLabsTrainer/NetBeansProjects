package com.tutego.insel.thread.concurrent;

import java.awt.Point;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ParallelPointInitSync {

  public static void main( String[] args ) {
    Lock lock = new ReentrantLock();
    Point p   = new Point();
    
    Runnable r = () -> {
      int rnd = (int) (Math.random() * 1000);

      while ( true ) {
        lock.lock();
    
        p.x = p.y = rnd;           // *
        int xc = p.x, yc = p.y;    // *
        
        if ( xc != yc )
          System.out.println( "Aha: x=" + xc + ", y=" + yc );
    
        lock.unlock();
      }
    };
    
    new Thread( r ).start();
    new Thread( r ).start();
  }
}