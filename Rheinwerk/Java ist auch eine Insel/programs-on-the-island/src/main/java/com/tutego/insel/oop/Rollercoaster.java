package com.tutego.insel.oop;
public class Rollercoaster {

  private static int numberOfInstances;

  {
    numberOfInstances++;
  }

  public static int getNumberOfInstances() {
    return numberOfInstances;
  }

  public static void main( String[] args ) {
    new Rollercoaster();
    new Rollercoaster();

    System.out.println( Rollercoaster.getNumberOfInstances() ); // 2
  }
}