package com.tutego.insel.oop;

class PizzaEater {

  int consumptionTime = 900 /* Seconds */;

  void eat() {
    System.out.printf( "I eat a pizza in %d seconds%n", consumptionTime );
  }
}

public class FuriousPete extends PizzaEater {
  
  int consumptionTime = 32 /* Seconds */;

  @Override void eat() {
    System.out.println( consumptionTime );        // 32
    System.out.println( super.consumptionTime );  // 900
    System.out.println( this.consumptionTime );   // 32
    System.out.println( ((PizzaEater) this).consumptionTime ); // 900

//    ((PizzaEater) this).eat();
  }

  public static void main( String[] args ) {
    new FuriousPete().eat();
  }
}
