package com.tutego.insel.exception;           /* 1 */
public class MissNumberFormatException {      /* 2 */
  public static int getVatRate() {            /* 3 */
    return Integer.parseInt( "19%" );         /* 4 */
  }                                           /* 5 */
  public static void main( String[] args ) {  /* 6 */
    System.out.println( getVatRate() );       /* 7 */
  }                                           /* 8 */
}                                             /* 9 */