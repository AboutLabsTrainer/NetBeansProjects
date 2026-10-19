package com.tutego.insel.game.c.vr;

class Event {}
class Workout extends Event {}

class Calendar {
  Event first() {
    return new Event();
  }
}

class WorkoutCalendar extends Calendar {
//  @Override Event first() {
  @Override Workout first() {
    return new Workout();
  }
}

public class DifferentCalendars {
  public static void main( String[] args ) {
    Event   first1 = new Calendar().first();
    Workout first2 = new WorkoutCalendar().first();
  }
}