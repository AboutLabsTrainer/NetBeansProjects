package com.tutego.insel.util.map;

public class LRUMapDemo {
  public static void main( String[] args ) {
    LRUMap<String, String> map = new LRUMap<>( 2 );
    map.put( "a", "A" );
    map.put( "b", "B" );
    System.out.println( map );  // {a=A, b=B}
    map.put( "c", "C" );
    System.out.println( map );  // {b=B, c=C}
    map.put( "b", "B" );
    System.out.println( map );  // {c=C, b=B}
    map.put( "d", "D" );
    System.out.println( map );  // {b=B, d=D}
  }
}
