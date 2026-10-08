package com.tutego.insel.httpserver;

import java.awt.Desktop;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HttpServerDemo {
  public static void main( String[] args ) throws IOException {
    HttpServer server = HttpServer.create( new InetSocketAddress( 8080 ), 0 );
    server.createContext( "/", new ChartHandler() );
    server.start();
    Desktop.getDesktop().browse( URI.create( "http://localhost:" + server.getAddress().getPort() ) );
  }
}

class ChartHandler implements HttpHandler {
  @Override
  public void handle( HttpExchange httpExchange ) throws IOException {
    httpExchange.getResponseHeaders().add( "Content-type", "text/html" );
    httpExchange.sendResponseHeaders( HttpURLConnection.HTTP_OK, 0 );

    try ( OutputStream os     = httpExchange.getResponseBody();
          Writer       writer = new OutputStreamWriter( os, StandardCharsets.UTF_8 ) ) {
      Logger.getGlobal().info( httpExchange.getRequestURI().toString() );

      writer.write( "<svg><polyline fill=\"none\" stroke=\"black\" points=\"" );
      writer.write( IntStream.range( 0, 800 )
                             .mapToObj( i -> i + "," + (int)(Math.random()*100) )
                             .collect( Collectors.joining( " " ) ) );
      writer.write( "\"/></svg>" );
    }
  }
}