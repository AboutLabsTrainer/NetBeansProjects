package com.tutego.insel.exception;
import java.io.*;

public class ReadGifSizeWithTryFinally {

  public static void printGifSize( String filename ) throws IOException {
    RandomAccessFile raf = new RandomAccessFile( filename, "r" );

    try {
      raf.seek( 6 );

      System.out.printf( "%s x %s Pixel%n", raf.read() + raf.read() * 256,
                                            raf.read() + raf.read() * 256 );
    } finally {
      raf.close();
    }
  }

  public static void main( String[] args ) throws IOException {
    printGifSize( "duke.gif" );
  }
}