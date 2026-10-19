package com.tutego.insel.net;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.jar.JarFile;

public class JarUrl {
  public static void main( String[] args ) throws IOException {
    Path path = Paths.get( System.getProperty("java.home" ), "lib", "jrt-fs.jar" );
    URL url = URI.create( "jar:" + path.toUri() + "!/" ).toURL();
    JarURLConnection conn = (JarURLConnection) url.openConnection();
    try ( JarFile jarFile = conn.getJarFile() ) {
      jarFile.stream().filter( jarEntry -> ! jarEntry.isDirectory() )
             .forEach( jarEntry -> System.out.printf( "%s, %s Byte(s)%n", jarEntry, jarEntry.getSize() ) );
    }
  }
}