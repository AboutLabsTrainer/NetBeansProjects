package com.tutego.insel.lambda;

import java.util.Arrays;
import java.util.function.Consumer;

class Consumers {
  public static <T> Consumer<T> executionTimeLogger( Consumer<T> block ) {
    return t -> {
      long start = System.nanoTime();
      block.accept( t );
      long duration = System.nanoTime() - start;
      System.getLogger( "executionTime" ).log(
          System.Logger.Level.INFO, "Execution time (ns): {0}", duration );
    };
  }

  public static void main( String[] args ) {
    Arrays.asList( 1, 2, 3, 4 ).forEach( executionTimeLogger( System.out::println ) );
  }
}