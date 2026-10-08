package com.tutego.insel.nio2;

import java.io.IOException;
import java.nio.file.*;

public class RealAndAbsolutePath {
  public static void main( String[] args ) {
    Path dotDotSlashDotDot = Paths.get( "../.." );
    System.out.println( dotDotSlashDotDot.toAbsolutePath() ); // C:\Users\Christian\Dropbox\workspace\..\..

    try {
      System.out.println( dotDotSlashDotDot.toRealPath( LinkOption.NOFOLLOW_LINKS ) ); // C:\Users\Christian\

      System.out.println( Paths.get( "../♡" ).toRealPath( LinkOption.NOFOLLOW_LINKS ) );
    }
    catch ( IOException e ) {
      e.printStackTrace();
    }
  }
}