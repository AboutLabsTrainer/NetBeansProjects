package com.tutego.insel.util.concurrent;

import java.util.concurrent.*;

import com.tutego.insel.util.concurrent.Ticket.Priority;

public class PrioritizedTicketsQueue {

  private static final BlockingQueue<Ticket> tickets =
    new PriorityBlockingQueue<>();

  private static final Runnable ticketProducer = () -> {
    while ( true ) {
      Priority priority = Priority.values()[ (int)(Math.random() * 2) ];
      Ticket ticket = new Ticket( priority, "Hilfe!" );
      tickets.add( ticket );
      System.out.println( "Neues Ticket: " + ticket );
      try { TimeUnit.MILLISECONDS.sleep( (int)(Math.random() * 2000) ); }
      catch ( InterruptedException e ) { /* Empty */ }
    }
  };

  private static final Runnable ticketSolver = () -> {
    while ( true ) {
      try {
        System.out.println( tickets.take() );
        TimeUnit.SECONDS.sleep( 1 );
      }
      catch ( InterruptedException e ) { /* Empty */ }
    }
  };

  public static void main( String[] args ) {
    new Thread( ticketProducer ).start();
    new Thread( ticketSolver ).start();
  }
}