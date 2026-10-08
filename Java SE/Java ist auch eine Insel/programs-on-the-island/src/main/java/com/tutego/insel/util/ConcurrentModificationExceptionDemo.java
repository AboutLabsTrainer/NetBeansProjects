package com.tutego.insel.util;

import java.util.*;

public class ConcurrentModificationExceptionDemo {
  public static void main( String[] args ) {
    List<String> list = new ArrayList<>( List.of( "Wubba", "Lubba" ) );
    Iterator<String> iter = list.iterator(); // modCount = 0, expectedModCount = 0
    list.get( 0 );                           // Anfragen ändert modCount nicht
    list.add( "Dub" );                       // modCount = 1, expectedModCount = 0
    iter.next();                             // modCount != expectedModCount => CME
    // Exception in thread "main" java.util.ConcurrentModificationException
  }
}