package com.tutego.insel.prefs;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.prefs.*;

public class PropertiesInRegistry {
  static Preferences prefs = Preferences.userRoot().node( "/com/tutego/insel" );
  
  static void fillRegistry() {
    System.getProperties().keySet().stream()
                                   .map( Objects::toString )
                                   .filter( key -> key.startsWith( "user." ) )
                                   .filter( key -> ! System.getProperty( key ).isEmpty() )
                                   .forEach( key -> prefs.put( key, System.getProperty( key ) ) );
  }

  static void display() {
    try {
      for ( String key : prefs.keys() )
       System.out.printf( "%s: %s%n", key, prefs.get( key, "---" ) );
    }
    catch ( BackingStoreException e ) {
      System.err.println( "Nodes cannot be read: " + e );
    }
  }

  static void saveAsXml() {
    ByteArrayOutputStream sos = new ByteArrayOutputStream();

    try {
      prefs.exportNode( sos );
      System.out.print( sos.toString( StandardCharsets.UTF_8 ) );
      System.out.printf( "Does the node exist? %b%n", prefs.nodeExists( "" ) );
    }
    catch ( IOException | BackingStoreException e ) {
      System.err.println( "Nodes can not be exported: " + e );
    }
  }

  static void removeFromRegistry() {
    try {
      prefs.removeNode();
      System.out.printf( "Does the node exist? %b%n", prefs.nodeExists( "" ) );
    }
    catch ( BackingStoreException e ) {
      System.err.println( "Nodes can not be deleted: " + e );
    }
  }

  static void addListener() {
    NodePreferenceChangeListener listener = new NodePreferenceChangeListener();

    prefs.addNodeChangeListener( listener );
    prefs.addPreferenceChangeListener( listener );
  }

  public static void main( String[] args ) {
    addListener();
    fillRegistry();
    display();
    saveAsXml();
    removeFromRegistry();
  }
}