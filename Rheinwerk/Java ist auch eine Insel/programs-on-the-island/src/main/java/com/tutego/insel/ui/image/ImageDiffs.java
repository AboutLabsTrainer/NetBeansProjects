package com.tutego.insel.ui.image;

import java.awt.*;
import java.awt.image.*;

public class ImageDiffs {
  public static void main( String[] args ) throws Exception {
    Rectangle screenSize = new Rectangle( Toolkit.getDefaultToolkit().getScreenSize() );

    DataBuffer dataBuffer1 =
      new Robot().createScreenCapture( screenSize ).getRaster().getDataBuffer();

    while ( true ) {
      Thread.sleep( 1000 );

      DataBuffer dataBuffer2 =
        new Robot().createScreenCapture( screenSize ).getRaster().getDataBuffer();

      int total = dataBuffer1.getSize(), diff = 0;

      for ( int i = 0; i < total; i++ )
        if ( dataBuffer1.getElem( i ) != dataBuffer2.getElem( i ) )
          diff++;

      System.out.printf( "Pixel total=%d, unterschiedliche Pixel=%d, Unterschied=%.2f%%%n", total,
                         diff, 100. * diff / total );

      dataBuffer1 = dataBuffer2;
    }
  }
}
