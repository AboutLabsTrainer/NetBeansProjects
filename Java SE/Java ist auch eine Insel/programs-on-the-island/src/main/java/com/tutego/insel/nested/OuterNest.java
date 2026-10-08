package com.tutego.insel.nested;

public class OuterNest {

  public static class In1 {
    private void intern1() {
      new OuterNest().new In2().intern2();
    }
  }

  public class In2 {
    private void intern2() {
      new In1().intern1();
    }
  }

  public static void main( String[] args ) {
    for ( Class<?> clazz : OuterNest.class.getNestMembers() ) {
      System.out.printf( "%s %b%n", clazz, clazz.isNestmateOf( OuterNest.class ) );
    }
  }
}