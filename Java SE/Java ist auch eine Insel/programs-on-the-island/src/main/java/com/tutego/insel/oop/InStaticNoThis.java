package com.tutego.insel.oop;

class InStaticNoThis {

  String name;

  void printName() {
    System.out.println( name );
  }
  
  public static void main( String[] args ) {
//    name = "Amanda";            // Cannot make a static reference to the non-static field name
//    printName();                // Cannot make a static reference to the non-static method printName() from the type InStaticNoThis
//    System.out.println( this ); // Cannot use this in a static context
  }
}
