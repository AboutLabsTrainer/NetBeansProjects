package com.tutego.insel.ws;

import javax.swing.JOptionPane;
import javax.xml.ws.Endpoint;

public class PublishWsOnServer {
  // Starten mit JVM Schalter --add-modules java.se.ee
  public static void main( String[] args ) {
    Endpoint endpoint = Endpoint.publish( "http://localhost:8080/services", new MyWebServices() );
    JOptionPane.showMessageDialog( null, "Server beenden" );
    endpoint.stop();
  }
}
