package com.tutego.insel.meta;

import java.io.IOException;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReader;
import java.lang.module.ModuleReference;

public class ModuleReferenceDemo {
  public static void main( String[] args ) throws IOException {
    
    ModuleReference moduleReference = ModuleFinder.ofSystem().find( "java.base" ).orElseThrow( IllegalStateException::new );
    ModuleReader reader = moduleReference.open();
    reader.list().sorted().limit( 10 ).forEach( System.out::println );
  }  
}
