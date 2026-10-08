package com.tutego.insel.ui.list;

import java.util.stream.Stream;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class JListDemo {
  public static void main( String[] args ) {
    JFrame frame = new JFrame( "Levels of Happiness" );
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    
    DefaultListModel<String> lykkeligModel = new DefaultListModel<>();
    Stream.of( ("1. Denmark,2. Switzerland,3. Austria,4. Iceland,5. Bahamas,"
               + "35. Germany,174. Ukraine,178. Burundi,Ende").split(",") )
          .forEach( lykkeligModel::addElement );
    JList<String> list = new JList<>( lykkeligModel );
    
    list.addListSelectionListener( e -> {
      if ( e.getValueIsAdjusting() )
        return;
    
      System.out.println( e );
    
      if ( "Ende".equals( lykkeligModel.get( e.getLastIndex() ) ) )
        System.exit( 0 );
    } );
    
    frame.add( new JScrollPane(list) );
    frame.pack();
    frame.setVisible( true );
  }
}