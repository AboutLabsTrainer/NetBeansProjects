package com.tutego.insel.records;

public record Rocket<T>(
    T value
) {
  public boolean isEmpty() { return value == null; }
}

class GenericRocketDemo {
  public static void main( String[] args ) {
    Rocket<String> r = new Rocket<>( "Tesla" );
    System.out.println( r.value() );
    System.out.println( r.isEmpty() );
  }
}