package com.tutego.insel.game.c.vc;

import java.util.Objects;

public class City {
  private String name;
  private int    population;

  public City( String name ) {
    setName( name );
  }

  public City( String name, int population ) {
    setName( name );
    setPopulation( population );
  }

  public void setName( String name ) {
    this.name = Objects.requireNonNull( name );
  }

  public String getName() {
    return name;
  }

  public void setPopulation( int population ) {
    if ( population >= 0 )
      this.population = population;
  }

  public int getPopulation() {
    return population;
  }
}
