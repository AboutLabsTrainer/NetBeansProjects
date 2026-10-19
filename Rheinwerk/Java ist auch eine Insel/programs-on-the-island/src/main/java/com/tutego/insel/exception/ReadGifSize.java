package com.tutego.insel.exception;
import java.io.*;

public class ReadGifSize {

  public static void main( String[] args ) {
    RandomAccessFile raf = null;

    try {
      raf = new RandomAccessFile( "duke.gif", "r" );
      raf.seek( 6 );

      System.out.printf( "%s x %s Pixel%n", raf.read() + raf.read() * 256,
                                            raf.read() + raf.read() * 256 );
    } catch ( FileNotFoundException e ) {
      System.err.println( "File does not exist!" );
    } catch ( IOException e ) {
      System.err.println( "General input/output error!" );
    } finally {
      if ( raf != null )
        try { raf.close(); } catch ( IOException e ) { }
    }
  }
}