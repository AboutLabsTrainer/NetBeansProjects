package com.tutego.insel.math;
import java.math.*;

public class DivideRoundingMode {
  public static void main( String[] args ) {
    BigDecimal one   = BigDecimal.ONE;
    BigDecimal three = new BigDecimal( "3" );
    
    System.out.println( one.divide( three, RoundingMode.UP ) );      // 1
    System.out.println( one.divide( three, RoundingMode.DOWN ) );    // 0
    
    System.out.println( one.divide( three, 6, RoundingMode.UP ) );   // 0.333334
    System.out.println( one.divide( three, 6, RoundingMode.DOWN ) ); // 0.333333
  }
}