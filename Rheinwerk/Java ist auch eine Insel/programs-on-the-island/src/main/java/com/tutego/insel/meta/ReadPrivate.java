package com.tutego.insel.meta;

public class ReadPrivate {

  @SuppressWarnings( "unused" )
  private String privateKey = "Snuggle bunny";

  public static void main( String[] args ) throws Exception {
    ReadPrivate key = new ReadPrivate();
    Class<?> c = key.getClass();

    java.lang.reflect.Field field = c.getDeclaredField( "privateKey" );
    field.setAccessible( true );

    System.out.println( field.get( key ) ); // Snuggle bunny
    field.set( key, "Sweetie pie" );

    System.out.println( field.get( key ) ); // Sweetie pie
  }
}
