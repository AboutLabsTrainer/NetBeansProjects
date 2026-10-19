package com.tutego.insel.nio2;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Optional;

public class CrawlForImages {
  public static void main( String[] args ) throws IOException {
    Files.walkFileTree( Paths.get( System.getProperty( "user.home" ) ),
                        new SimpleFileVisitor<Path>()
    {
      @Override
      public FileVisitResult visitFileFailed( Path file, IOException exc ) {
        return FileVisitResult.SKIP_SUBTREE;
      }      
    
      @Override
      public FileVisitResult visitFile( Path path, BasicFileAttributes attribs ) {
        try {
          Optional.ofNullable( Files.probeContentType( path ) )
                  .filter( mime -> mime.startsWith( "image/" ) )
                  .ifPresent( __ -> System.out.println(path) );
        }
        catch ( IOException e ) { }
    
        return FileVisitResult.CONTINUE;
      }
    } );
  }
}