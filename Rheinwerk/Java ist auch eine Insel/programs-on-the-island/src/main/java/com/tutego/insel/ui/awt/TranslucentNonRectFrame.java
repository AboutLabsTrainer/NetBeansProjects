package com.tutego.insel.ui.awt;

import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class TranslucentNonRectFrame {
  public static void main( String[] args ) {
    JFrame f = new JFrame();
    f.setUndecorated( true );
    f.setOpacity( 0.5F );
    f.setShape( new Ellipse2D.Float( 0.0F, 0.0F, 100.0F, 100.0F ) );
    f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    f.add( new JTextArea() );
    f.setBounds( 500, 200, 100, 100  );
    f.setVisible( true );
  }
}
