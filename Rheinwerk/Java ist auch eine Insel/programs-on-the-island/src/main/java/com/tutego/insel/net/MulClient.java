package com.tutego.insel.net;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import static java.nio.charset.StandardCharsets.UTF_8;

class MulClient {

  private static String multiply( String factor1, String factor2 ) throws IOException {
    try ( Socket socket = new Socket( "localhost", 3141 );
          Scanner in    = new Scanner( socket.getInputStream(), UTF_8 );
          Writer osw    = new OutputStreamWriter( socket.getOutputStream(), UTF_8 );
          PrintWriter out = new PrintWriter( osw, true ) ) {
      out.println( factor1 );
      out.println( factor2 );
      return in.nextLine();
    }
  }

  public static void main( String[] args ) throws IOException {
    System.out.println( multiply( "2", "4" ) );
    System.out.println( multiply( "23895737895", "434589358935857" ) );
  }
}