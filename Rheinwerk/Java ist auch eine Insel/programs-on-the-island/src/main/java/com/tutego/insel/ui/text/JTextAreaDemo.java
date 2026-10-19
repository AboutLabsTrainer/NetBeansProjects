package com.tutego.insel.ui.text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JTextAreaDemo extends JFrame {

  private final JTextArea textArea = new JTextArea();

  public JTextAreaDemo() {
    add( new JScrollPane( textArea ) );

    JPanel panel = new JPanel( new GridLayout(1, 3) );
    add( panel, BorderLayout.PAGE_START );

    AbstractButton button;

    panel.add( button = new JToggleButton("fett") );
    button.addActionListener( this::onFontChanged );
    button.setFont( button.getFont().deriveFont( Font.BOLD ) );

    panel.add( button = new JToggleButton("kursiv") );
    button.addActionListener( this::onFontChanged );
    button.setFont( button.getFont().deriveFont( Font.ITALIC ) );

    panel.add( button = new JButton("Ende") );
    button.addActionListener( this::onFontChanged );

    setSize( 400, 600 );
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
  }

  private void onFontChanged( ActionEvent e ) {
    Font textFont = textArea.getFont();
    switch ( e.getActionCommand() ) {
      case "fett":
        textArea.setFont( textFont.deriveFont( textFont.getStyle() ^ Font.BOLD ) );
        break;
      case "kursiv":
        textArea.setFont( textFont.deriveFont( textFont.getStyle() ^ Font.ITALIC ) );
        break;
      case "Ende":
        System.exit( 0 );
    }
  }

  public static void main( String[] args ) {
    new JTextAreaDemo().setVisible( true );
  }
}