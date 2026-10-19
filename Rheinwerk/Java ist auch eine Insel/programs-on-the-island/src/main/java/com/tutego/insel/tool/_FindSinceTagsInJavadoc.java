//package com.tutego.insel.tool;
//
//import java.io.IOException;
//import java.nio.file.*;
//import java.util.Arrays;
//import java.util.List;
//import java.util.function.Predicate;
//import java.util.stream.Collectors;
//import javax.tools.*;
//import com.sun.javadoc.*;
//
//@SuppressWarnings( "removal" )
//public class _FindSinceTagsInJavadoc {
//
//  public static class TestDoclet {
//
//    @SuppressWarnings( "deprecation" )
//    public static boolean start( RootDoc root ) {
//      Arrays.stream( root.classes() ).forEach( TestDoclet::processClass );
//      return true;
//    }
//
//    @SuppressWarnings( "deprecation" )
//    private static void processClass( ClassDoc clazz ) {
//
//      Predicate<Doc> hasSinceTag = doc -> Arrays.stream( doc.tags( "since" ) )
//                                            .map( Tag::text ).anyMatch( "11"::equals );
//
//      if ( hasSinceTag.test( clazz ) )
//        System.out.printf( "%s -- Neuer Typ%n", clazz );
//
//      Arrays.stream( clazz.fields() ).filter( hasSinceTag )
//            .forEach( f -> System.out.printf( "%s -- Neues Attribut%n", f ) );
//
//      Arrays.stream( clazz.methods() ).filter( hasSinceTag )
//            .forEach( m -> System.out.printf( "%s -- Neue Methode%n", m ) );
//
//      Arrays.stream( clazz.constructors() ).filter( hasSinceTag )
//            .forEach( c -> System.out.printf( "%s -- Neuer Konstruktor%n", c ) );
//    }
//  }
//
//  public static void main( String[] args ) throws IOException {
//
//    Path zipfile = Paths.get( System.getProperty( "java.home" ), "lib/src.zip" );
//    try ( FileSystem srcFs = FileSystems.newFileSystem( zipfile, null ) ) {
//      Predicate<Path> filesToIgnore = p ->  p.toString().endsWith( ".java" ) &&
//                                          ! p.toString().startsWith( "/javafx" ) &&
//                                          ! p.toString().startsWith( "/jdk" ) &&
//                                          ! p.toString().endsWith( "module-info.java" ) &&
//                                          ! p.toString().endsWith( "package-info.java" );
//
//      List<Path> paths = Files.walk( srcFs.getPath( "/" ) )
//                              .filter( filesToIgnore ).collect( Collectors.toList() );
//
//      DocumentationTool tool = ToolProvider.getSystemDocumentationTool();
//      try ( StandardJavaFileManager fm = tool.getStandardFileManager( null, null, null ) ) {
//        Iterable<? extends JavaFileObject> files = fm.getJavaFileObjectsFromPaths( paths );
//        tool.getTask( null, fm, null, TestDoclet.class, List.of( "-quiet" ), files ).call();
//      }
//    }
//  }
//}
