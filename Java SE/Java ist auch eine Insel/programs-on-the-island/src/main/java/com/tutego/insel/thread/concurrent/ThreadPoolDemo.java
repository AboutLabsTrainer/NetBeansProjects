package com.tutego.insel.thread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
  
  public static void main( String[] args ) throws InterruptedException {
    Runnable r1 = () -> {
      System.out.println( "1.1 " + Thread.currentThread().getName() );
      System.out.println( "1.2 " + Thread.currentThread().getName() );
    };
    
    Runnable r2 = () -> {
      System.out.println( "2.1 " + Thread.currentThread().getName() );
      System.out.println( "2.2 " + Thread.currentThread().getName() );
    };

    ExecutorService executor = Executors.newCachedThreadPool();

    executor.execute( r1 );
    executor.execute( r2 );

    Thread.sleep( 500 );

    executor.execute( r1 );
    executor.execute( r2 );

    executor.shutdown();
  }
}
