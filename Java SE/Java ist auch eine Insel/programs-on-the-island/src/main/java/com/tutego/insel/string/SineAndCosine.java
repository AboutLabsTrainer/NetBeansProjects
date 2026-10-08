package com.tutego.insel.string;

public class SineAndCosine {

  public static void main( String[] args ) {
    String s = javax.swing.JOptionPane.showInputDialog( "Input floating decimal" );
    double value = Double.parseDouble( s );
    System.out.println( "Sine: " + Math.sin( value ) );
    System.out.println( "Cosine: " + Math.cos( value ) );
  }
}
