package com.tutego.insel.io.stream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class OutputStreamDemo {

  public static void main( String[] args ) {
    byte[] bytes = { 'O', 'N', 'A', 'L', 'D' };
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try {
      out.write( 'D' );          // schreibe D
      out.write( bytes );        // schreibe ONALD
      out.write( bytes, 1, 2 );  // schreibe NA
      System.out.println( out.toString( StandardCharsets.ISO_8859_1.name() )  );
    }
    catch ( IOException e ) {
      e.printStackTrace();
    }
  }
}
