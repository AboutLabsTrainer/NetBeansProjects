package com.tutego.insel.pattern.listener;

public class RadioDemo {
  
  public static void main( String[] args ) {
    Radio r = new Radio();
    
    class ComplainingAdListener implements AdListener {
      @Override public void advertisement( AdEvent e ) {
        System.out.println( "Oh no, advertising again: " + e.getSlogan() );
      }
    }
    
    r.addAdListener( new ComplainingAdListener() );
    r.addAdListener( e -> System.out.println( "I hear nothing" ) );
  }
}