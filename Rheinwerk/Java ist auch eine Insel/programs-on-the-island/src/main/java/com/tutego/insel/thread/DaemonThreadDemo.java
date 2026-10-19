package com.tutego.insel.thread;

class DaemonThreadDemo {
  public static void main( String[] args ) {

    Runnable endless = () -> {
      while ( true )
        System.out.println( "Lauf Thread, lauf" );
    };
    
    Thread thread = new Thread( endless );
    thread.setDaemon( true );
    thread.start();
  }
}
