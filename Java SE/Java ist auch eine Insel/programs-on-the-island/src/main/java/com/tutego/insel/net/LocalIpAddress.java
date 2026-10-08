package com.tutego.insel.net;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;

public class LocalIpAddress {
  public static void main( String[] args ) throws SocketException {
    
    Collections.list( NetworkInterface.getNetworkInterfaces() ).forEach( ni -> {
      int n = 0;
      System.out.println( "NetworkInterface " + n++ + ": " + ni.getDisplayName() );
    
      for ( InetAddress iaddress : Collections.list( ni.getInetAddresses() ) ) {
        System.out.println( "CanonicalHostName: " + iaddress.getCanonicalHostName() );
        System.out.println( "IP: " + iaddress.getHostAddress() );
        System.out.println( "Loopback? " + iaddress.isLoopbackAddress() );
        System.out.println( "SiteLocal? " + iaddress.isSiteLocalAddress() );
        System.out.println();
      }
    } );
  }
}
