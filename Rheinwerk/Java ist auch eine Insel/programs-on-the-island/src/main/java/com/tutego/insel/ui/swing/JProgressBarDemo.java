package com.tutego.insel.ui.swing;

import java.awt.BorderLayout;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class JProgressBarDemo {

  public static void main( String[] args ) {
    JFrame f = new JFrame();
    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    
    JProgressBar bar = new JProgressBar( 0, 1000000 );
    bar.setStringPainted( true );

    Thread backgroundThread = new Thread( () -> {
      AtomicInteger value = new AtomicInteger( 1 );
      while ( value.get() <= bar.getMaximum() )
        SwingUtilities.invokeLater( () -> bar.setValue( value.getAndIncrement() ) );
    } );
    
    JButton but = new JButton( "Start zählen" );
    but.addActionListener( e -> backgroundThread.start() );
    
    f.add( bar, BorderLayout.PAGE_START );
    f.add( but, BorderLayout.PAGE_END );
    
    f.pack();
    f.setVisible( true );
  }
}