package com.tutego.insel.ui.event;

import java.awt.FlowLayout;
import java.util.Optional;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Hexconverter {
  public static void main( String[] args ) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    frame.setLayout( new FlowLayout() );

    final JTextField decTextField = new JTextField();
    decTextField.setColumns( 5 );
    frame.add( decTextField );
    frame.add( new JLabel( " ist hexadezimal " ) );
    final JTextField hexTextField = new JTextField();
    hexTextField.setColumns( 5 );
    frame.add( hexTextField );
    JButton okButton = new JButton( "Konvertiere" );
    frame.add( okButton );

    okButton.addActionListener( e -> Optional.of( decTextField.getText() )
                                             .filter( Pattern.compile( "\\d+" ).asPredicate() )
                                             .map( Integer::parseInt )
                                             .map( Integer::toHexString )
                                             .ifPresent( hexTextField::setText ) );

    frame.pack();
    frame.setVisible( true );
  }
}