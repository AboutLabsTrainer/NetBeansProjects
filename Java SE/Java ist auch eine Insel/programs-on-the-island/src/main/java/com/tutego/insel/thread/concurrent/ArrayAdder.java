package com.tutego.insel.thread.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Collectors;

public class ArrayAdder {

  public static void main( String[] args ) {
    int[] array = new Random().ints( 0, 100 ).limit( 10_000_000 ).toArray();
    addParallel( array );
  }

  public static void addParallel( int[] array ) {
    int processors = 4; // Runtime.getRuntime().availableProcessors();

    List<Long> longs = new ArrayList<>();

    Runnable merger = () -> {
      System.out.print( longs.stream().map( Object::toString ).collect( Collectors.joining(" + ")) );
      long sum = longs.stream().mapToLong( Long::longValue ).sum();
      System.out.println( " = " + sum );
    };

    CyclicBarrier barrier = new CyclicBarrier( processors, merger );

    for ( int part = 0; part < processors; part++ )
      new Thread( new Adder( barrier, array, processors, part, longs ) ).start();
  }
}

class Adder implements Runnable {
  
  private final CyclicBarrier barrier;
  private final int[] array;
  private final List<Long> longs;
  private final int start, end;

  public Adder( CyclicBarrier barrier, int[] array, int maxPart, int currentPart,
                List<Long> longs ) {
    this.barrier = barrier;
    this.array   = array;
    this.longs   = longs;

    start = (int) ((double) array.length / maxPart * currentPart);
    end = (int) ((double) array.length / maxPart * (currentPart + 1) - 1);
  }

  @Override
  public void run() {
    System.out.printf( "Adding from [%d to %d]%n", start, end );
    long sum = 0;
    for ( int i = start; i < end; i++ )
      sum += array[ i ];

    longs.add( sum );

    try {
      barrier.await();
    }
    catch ( InterruptedException | BrokenBarrierException e ) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    }
  }
}