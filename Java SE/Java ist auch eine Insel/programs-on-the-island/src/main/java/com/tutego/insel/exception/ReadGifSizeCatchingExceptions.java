package com.tutego.insel.exception;
import java.io.*;

public class ReadGifSizeCatchingExceptions {

  public static void main( String[] args ) {
    try {
      RandomAccessFile raf = new RandomAccessFile( "duke.gif", "r" );
      raf.seek( 6 );
      
      System.out.printf( "%s x %s Pixel%n", raf.read() + raf.read() * 256,
                                            raf.read() + raf.read() * 256 );
      raf.close();
    } catch ( FileNotFoundException e ) {
      System.err.println( "File does not exist!" );
    } catch ( IOException e ) {
      System.err.println( "General input/output error!" );
    }
  }
}