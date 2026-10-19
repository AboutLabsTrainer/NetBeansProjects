package com.tutego.insel.exception;

import java.io.IOException;
import java.io.RandomAccessFile;

public class ReadGifSizeTryWithResources {
  public static void main( String[] args ) {
    try ( RandomAccessFile raf = new RandomAccessFile( "duke.gif", "r" ) ) {
      raf.seek( 6 );
      System.out.printf( "%s x %s Pixel%n", raf.read() + raf.read() * 256,
                                            raf.read() + raf.read() * 256 );
    }
    catch ( IOException e ) {
      System.err.println( "Input/output error!" );
    }
  }
}