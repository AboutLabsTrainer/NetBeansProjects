package com.tutego.insel.net.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class HttpClientDemo {

  public static void main( String[] args ) {
    try {
      URI uri = new URI( "https://tutego.de/" );
      HttpRequest request = HttpRequest.newBuilder().uri( uri ).GET().build();
      HttpResponse<String> response =
        HttpClient.newHttpClient().send( request, BodyHandlers.ofString() );
      System.out.println( response.body() );
    }
    catch ( URISyntaxException | IOException | InterruptedException e ) {
      e.printStackTrace();
    }
  }
}
