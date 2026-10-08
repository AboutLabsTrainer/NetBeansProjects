package com.tutego.insel.iterable;

public class WordIterableDemo {
  
  public static void main( String[] args ) {
    String s = "Even miracles take a little time.";

    for ( String word : new WordIterable(s) )
      System.out.println( word );
  }
}
