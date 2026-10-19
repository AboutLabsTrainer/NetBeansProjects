package com.tutego.insel.thread.concurrent;

import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorCompletionServiceDemo {
  public static void main( String[] args ) {

    ExecutorService executor = Executors.newCachedThreadPool();
    CompletionService<Integer> completionService =
      new ExecutorCompletionService<>( executor );
    List.of( 4, 3, 2, 1 ).forEach( duration -> completionService.submit( () -> {
      TimeUnit.SECONDS.sleep( duration );
      return duration;
    } ) );
    
    for ( int i = 0; i < 4; i++ ) {
      try {
        System.out.println( completionService.take().get() );
      }
      catch ( InterruptedException | ExecutionException e ) {
        e.printStackTrace();
      }
    }
    
    executor.shutdown();
  }
}
