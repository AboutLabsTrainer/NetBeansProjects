package com.tutego.insel.thread.concurrent;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class HostReachability {
  public static void main( String[] args )  {

    List<String> hosts = List.of( "popelnase.de", "tutego.de" );
    CountDownLatch latch = new CountDownLatch( hosts.size() );
    
    for ( String host : hosts ) {
      new Thread( () -> {
        try {
          if ( InetAddress.getByName( host ).isReachable( 900 /* ms */ ) )
            latch.countDown();
        }
        catch ( IOException e ) { /* ignore */ }
      } ).start();
    }
    
    try {
      if ( latch.await( 1, TimeUnit.SECONDS ) )
        System.out.println( "Alle Hosts erreicht" );

      System.out.printf( "%d von %d Hosts nicht erreicht", latch.getCount(), hosts.size() );
    }
    catch ( InterruptedException e ) {
      e.printStackTrace();
    }
  }
}
