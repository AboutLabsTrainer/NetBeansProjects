package com.tutego.insel.thread.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class WrappedUsername {

  public static void main( String[] args ) throws InterruptedException, ExecutionException {

    Callable<String> username = () -> System.getProperty( "user.name" );
    FutureTask<String> wrappedUsername = new FutureTask<>( username ) {
      @Override
      protected void done() {
        try {
          System.out.printf( "done: isDone=%s, isCancelled=%s%n", isDone(), isCancelled() );
          System.out.println( "done: get=" + get() );
        }
        catch ( InterruptedException | ExecutionException e ) { /* Ignore */ }
      }

      @Override protected void set( String v ) {
        System.out.println( "set: " + v );
        super.set( v.toUpperCase() );
      }
    };
    ExecutorService scheduler = Executors.newCachedThreadPool();
    scheduler.submit( wrappedUsername );
    System.out.println( "main:" + wrappedUsername.get() );
    scheduler.shutdown();
  }
}
