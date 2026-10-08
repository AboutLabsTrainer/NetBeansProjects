package com.tutego.insel.nested;

class AlmondJoy {

  String name = "Almond Joy";
  private int introduced = 1946;

  class Coconut {
    void print() {
      System.out.println( name );
      System.out.println( introduced );
    }
  }

  public static void main( String[] args ) {
    AlmondJoy almondJoy = new AlmondJoy();
    Coconut coconut = almondJoy.new Coconut();
    Coconut coconut1 = new AlmondJoy().new Coconut();
  }
}
