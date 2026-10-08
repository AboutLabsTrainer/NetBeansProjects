package com.tutego.insel.script;

import java.util.List;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;
import jdk.jshell.VarSnippet;

public class JShellDemo {
  public static void main( String[] args ) {
    try ( JShell shell = JShell.create() ) {
      // Semikolon wichtig!
      String program = "java.math.BigInteger.TEN.pow( 10 );";
      List<SnippetEvent> events = shell.eval( program );
      for ( SnippetEvent snippetEvent : events ) {
        System.out.println( snippetEvent.status() );
        System.out.println( snippetEvent.value() );
        System.out.println( snippetEvent.snippet().source() );
        System.out.println( snippetEvent.snippet().kind() );
        if ( snippetEvent.snippet() instanceof VarSnippet ) {
          VarSnippet varSnippet = (VarSnippet) snippetEvent.snippet();
          System.out.println( varSnippet.typeName() );
        }
      }
    }
  }
}
