package com.tutego.insel.net;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.Scanner;
import static java.nio.charset.StandardCharsets.UTF_8;

public class MulServer {

  public static void main( String[] args ) throws IOException {
    try ( ServerSocket server = new ServerSocket( 3141 ) ) {
      while ( true )
        try ( Socket client = server.accept() ) {
          handleConnection( client );
        }
        catch ( IOException e ) {
          e.printStackTrace();
        }
    }
  }
  
  private static void handleConnection( Socket client ) throws IOException {
    try ( Scanner in = new Scanner( client.getInputStream(), UTF_8 );
          Writer osw = new OutputStreamWriter( client.getOutputStream(), UTF_8 );
          PrintWriter out = new PrintWriter( osw, true ) ) {
      String factor1 = in.nextLine();
      String factor2 = in.nextLine();
      out.println( new BigInteger( factor1 ).multiply( new BigInteger( factor2 ) ) );
    }
  }
}