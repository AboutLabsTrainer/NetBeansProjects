package com.tutego.insel.util.queue;

import java.util.*;

public class QueueDemo {
  public static void main( String[] args ) {
    Queue<String> queue = new LinkedList<>();
    Arrays.asList( "Fischers", "Fischers", "fischt", "frische", "Fische" ).forEach( queue::offer );

    queue.poll();

    queue.offer( "Nein, es war Paul!" );

    while ( !queue.isEmpty() )
      System.out.println( queue.poll() );
  }
}
