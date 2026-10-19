package com.tutego.insel.thread.group;

import java.util.Arrays;

public class ThreadInThreadGroup {
  static public void main( String[] args ) {

    Runnable runnable = () -> {
      for ( int i = 0; i < 3; i++ )
        System.err.printf( "%s: Ich bin der Schrecken, der die Nacht durchflattert%n",
                           Thread.currentThread().getName() );
    };
    
    ThreadGroup group = new ThreadGroup( "Helden" );
    new Thread( group, runnable, "Darkwing Duck" ).start();
    new Thread( group, runnable, "Kikky" ).start();
    
    // Fülle Array mit allen Threads der Gruppe
    Thread[] threads = new Thread[ group.activeCount() ];
    group.enumerate( threads );
    
    // Eigenes Auflisten der Gruppe
    Arrays.stream( threads ).forEach( System.out::println );
    
    // Eingebaute list()-Methode nutzen    
    group.list();
  }
}
