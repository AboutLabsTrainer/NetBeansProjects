package com.tutego.date4u.core.photo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service // https://gist.github.com/ullenboom/8860ceaafbe5bd904028d94dff5039de
public class Thumbnail {

  private final Logger log = LoggerFactory.getLogger( getClass() );

  public byte[] createThumbnail( byte[] imageBytes ) {
    try ( var inputStream = new ByteArrayInputStream( imageBytes );
          var byteArrayOutputStream = new ByteArrayOutputStream() ) {
      var img = ImageIO.read( inputStream );
      float scale = Math.min( 256f / img.getWidth(), 256f / img.getHeight() );
      int width = Math.round( img.getWidth() * scale ),
          height = Math.round( img.getHeight() * scale );
      var thumbImg = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );
      var graphics = thumbImg.createGraphics();
      graphics.drawImage( img, 0, 0, width, height, null );
      graphics.dispose();
      ImageIO.write( thumbImg, "jpg", byteArrayOutputStream );
      return byteArrayOutputStream.toByteArray();
    }
    catch ( IOException e ) {
      throw new UncheckedIOException( e );
    }
  }

  @Async
  public Future<byte[]> createThumbnailAsync( byte[] imageBytes ) {
    log.info( "Creating async thumbnail" );
    try {
      return CompletableFuture.completedFuture( createThumbnail( imageBytes ) );
    }
    catch ( Exception e ) {
      return CompletableFuture.failedFuture( e );
    }
  }
}
