package com.tutego.insel.meta;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModuleDemo {

  public static void main( String[] args ) throws IOException {
//    Module stringModule = String.class.getModule();
//    System.out.println( stringModule );
//
//    System.out.println( ModuleDemo.class.getModule() );
//
//    stringModule.getPackages().stream().sorted().limit( 10 ).forEach( System.out::println );

//    System.out.println( javaBaseModules );
//    javaBaseModules.stream().map( module -> module.getName() ).sorted()
//            .forEach( System.out::println );

//    Module javaDesktopModule = ModuleLayer.boot().findModule( "java.desktop" ).orElseThrow( IllegalStateException::new );
//    Module javaXmlModule = ModuleLayer.boot().findModule( "java.xml" ).orElseThrow( IllegalStateException::new );
//    System.out.println( javaDesktopModule.canRead( javaXmlModule ) );
//    System.out.println( javaXmlModule.canRead( javaDesktopModule ) );

    Module[] array = ModuleLayer.boot().modules().stream()
                                .filter( m -> m.getName().startsWith( "java." ) )
                                .toArray( Module[]::new );
    System.out.println( "digraph G {\nnode [style=filled];\n" );
    for ( int i = 0; i < array.length; i++ ) {
      for ( int j = 0; j < array.length; j++ ) {
        if ( i != j && array[i].canRead( array[j] ) )
          System.out.printf( "\"%s\" -> \"%s\";%n", array[i].getName(), array[j].getName() );
      }      
    }

    Function<Module, Integer>  numberOfDots = module -> module.getName().replaceAll( "[^.]", "" ).length();
    Function<Module, String> valueMapper = numberOfDots.andThen( len -> List.of("", "red", "orange").get( len ) );
    Stream.of( array ).collect( Collectors.toMap( Module::getName, valueMapper ) )
                      .forEach( (k,v) -> System.out.printf( "\"%s\" [color=\"%s\"];%n", k, v ) );

    System.out.println( "}" );
    
  }
}
