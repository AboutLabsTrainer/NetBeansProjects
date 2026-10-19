package com.tutego.insel.util.concurrent;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;

public class LoggingInQueue {

  private static final BlockingQueue<String> messages =
    new LinkedBlockingQueue<>();

  private static final Runnable messageOutputter = () -> {
    while ( true )
      try {
        long startTime = System.currentTimeMillis();
        System.out.printf( "%s (Wartete %d ms)%n",
                           messages.take(),
                           System.currentTimeMillis() - startTime );
      }
      catch ( InterruptedException e ) { }
  };

  private static final Runnable userMessageProducer = () -> {
    for( int i = 0; ; i++ )
      messages.add( "msg " + i + " " +
                    JOptionPane.showInputDialog( "Meldung eingeben" ) );
  };

  private static final Runnable diskspaceMessageProducer = () -> {
    try {
      String dir = System.getProperty( "user.dir" );
      for ( int i = 0;; i++ ) {
        messages.add( "spc " + i + " " +
                      Files.getFileStore( Paths.get( dir ) ).getUsableSpace() );
        TimeUnit.SECONDS.sleep( 1 );
      }
    }
    catch ( Exception e ) { e.printStackTrace(); }
  };

  public static void main( String[] args ) {
    new Thread( messageOutputter ).start();
    new Thread( userMessageProducer ).start();
    new Thread( diskspaceMessageProducer ).start();
  }
}