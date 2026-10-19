package com.tutego.insel.exception;
import java.io.*;

public class ReadGifSizeIgnoringExceptions {

  @SuppressWarnings( "resource" )
  public static void main( String[] args ) throws IOException {
    RandomAccessFile raf = new RandomAccessFile( "duke.gif", "r" );
    raf.seek( 6 );

    System.out.printf( "%s x %s Pixel%n", raf.read() + raf.read() * 256,
                                          raf.read() + raf.read() * 256 );
  }
}
