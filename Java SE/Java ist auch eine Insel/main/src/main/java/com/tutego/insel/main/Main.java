package com.tutego.insel.main;

import com.tutego.insel.candy.YummyTester;
import javax.swing.*;

public class Main {
  public static void main( String[] args ) {
    System.out.println( YummyTester.testYumminess( "Chris" ) ); // false
    JOptionPane.showMessageDialog( null, YummyTester.testYumminess( "Meiji" ) ); // true
  }
}
