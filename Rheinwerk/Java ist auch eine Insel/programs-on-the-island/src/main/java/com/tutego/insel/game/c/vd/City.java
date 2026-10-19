package com.tutego.insel.game.c.vd;

public class City {
  public String name;
  public int    population;

  public City( String name, int population ) {
    this.name = name.trim();	
    this.population = Math.max( 0, population );
  }

  public City( City other ) {
    this.name = other.name.trim();
    this.population = Math.max( 0, other.population );
  }
}
