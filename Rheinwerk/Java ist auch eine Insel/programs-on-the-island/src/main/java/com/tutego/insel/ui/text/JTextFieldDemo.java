package com.tutego.insel.ui.text;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class JTextFieldDemo {
  public static void main( String[] args ) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    JTextField input  = new JTextField( "", 20 );
    input.addActionListener( __ -> input.setText( input.getText().toUpperCase() ) );

    frame.add( input );
    frame.pack();
    frame.setVisible( true );
  }
}
