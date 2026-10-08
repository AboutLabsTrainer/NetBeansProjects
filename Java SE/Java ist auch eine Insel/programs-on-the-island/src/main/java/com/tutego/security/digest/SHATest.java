package com.tutego.security.digest;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

class SHATest {
  public static void main( String[] args ) throws Exception {
    MessageDigest md = MessageDigest.getInstance( "SHA" );    
    for ( String text : List.of( "abc", "Abc", "abd" ) ) {
      byte[] digest = md.digest( text.getBytes( StandardCharsets.UTF_8 ) );
      System.out.printf( "%032X%n", new BigInteger( 1, digest ) );
    }
  }
}