package com.tutego.insel.game.c.vh;

public class City {
  public final String name;
  public final int population;

  public City( String name, int population ) {
    this.name = name;
    this.population = population;
  }

  public City withName( String name ) {
    return new City( name, population );
  }

  public City withPopulation( int population ) {
    return new City( name, population );
  }
}
