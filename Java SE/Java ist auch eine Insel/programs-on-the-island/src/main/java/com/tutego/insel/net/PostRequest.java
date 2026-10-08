package com.tutego.insel.net;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class PostRequest {
  public static void main( String[] args ) throws IOException {
    String body = "param1=" + URLEncoder.encode( "value1", StandardCharsets.UTF_8 ) + "&" +
                  "param2=" + URLEncoder.encode( "value2", StandardCharsets.UTF_8 );
    
    URL url = URI.create( "http://li.la.lu.lo/post/" ).toURL();
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod( "POST" );
    connection.setDoInput( true );
    connection.setDoOutput( true );
    connection.setUseCaches( false );
    connection.setRequestProperty( "Content-Type",
                                   "application/x-www-form-urlencoded" );
    connection.setRequestProperty( "Content-Length", String.valueOf(body.length()) );

    try ( Writer writer = new OutputStreamWriter( connection.getOutputStream() ) ) {
      writer.write( body );
      writer.flush();
    
      try ( Scanner scanner = new Scanner( connection.getInputStream() ) ) {      
        while ( scanner.hasNextLine() )
          System.out.println( scanner.nextLine() );
      }
    }
  }
}
