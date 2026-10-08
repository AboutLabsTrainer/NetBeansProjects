package com.tutego.insel.oop;

class HardCandy {
  HardCandy() {
    printType();
  }
  void printType() {
    System.out.println( "I do not know yet :-(" );
  }
}

public class KingOfDenmark extends HardCandy {

  String type = "I am King of Denmark";

//  KingOfDenmark() {
//    super();
//    type = "I am King of Denmark";
//  }

  @Override
  void printType() {
    System.out.println( type );
  }

  public static void main( String[] args ) {
    HardCandy candy = new HardCandy();
    candy.printType();

    KingOfDenmark kingOfDenmark = new KingOfDenmark();
    kingOfDenmark.printType();
  }
}