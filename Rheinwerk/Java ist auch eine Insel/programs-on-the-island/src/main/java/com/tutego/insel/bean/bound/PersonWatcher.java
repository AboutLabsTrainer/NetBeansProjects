package com.tutego.insel.bean.bound;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PersonWatcher {
  
  public static void main( String[] args ) {
    PropertyChangeListener psl = (PropertyChangeEvent e) ->
      System.out.printf( "Property '%s': '%s' -> '%s'%n",
      e.getPropertyName(), e.getOldValue(), e.getNewValue() );
    Person p = new Person();
    p.addPropertyChangeListener( psl );
    p.setName( "Ulli" );   // Property 'name': '' -> 'Ulli'
    p.setName( "Ulli" );
    p.setName( "Chris" );  // Property 'name': 'Ulli' -> 'Chris'
  }
}