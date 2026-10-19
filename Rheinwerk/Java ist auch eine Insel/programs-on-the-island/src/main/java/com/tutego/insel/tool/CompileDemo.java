package com.tutego.insel.tool;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class CompileDemo {

  public static void main( String[] args ) throws Exception {
    
    Path javaSrc = Paths.get( "A.java" );
    
    String code = "class A { static { System.out.println(\"Java Compiler API\"); } }";
    Files.write( javaSrc, Collections.singleton(code) );
    
    //
    
    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

    try ( StandardJavaFileManager fm = compiler.getStandardFileManager(
            null /*DiagnosticListener*/, null /*Locale*/, null /*Charset*/ ) ) {
      Iterable<? extends JavaFileObject> files =
          fm.getJavaFileObjectsFromStrings( Collections.singleton( javaSrc.toString() ) );
      CompilationTask task = compiler.getTask(
        null /*Writer*/, fm, null /*DiagnosticListener*/, null /*Iterable<String>*/,
        null /*Iterable<String>*/, files );
      task.call();
    }
    
    //
    
    URL[] url = { javaSrc.toAbsolutePath().getParent().toUri().toURL() };
    try ( URLClassLoader classLoader = new URLClassLoader( url ) ) {
      Class.forName( "A", true, classLoader );    // Java Compiler API
    }

    Files.delete( javaSrc );
  }
}