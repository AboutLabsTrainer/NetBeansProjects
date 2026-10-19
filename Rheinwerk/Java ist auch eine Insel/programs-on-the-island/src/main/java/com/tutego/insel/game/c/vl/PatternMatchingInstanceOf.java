package com.tutego.insel.game.c.vl;

public class PatternMatchingInstanceOf {

  static class Variant1 {
    static boolean burnedSameCalories( Event event1, Event event2 ) {
      if ( !(event1 instanceof Workout && event2 instanceof Workout) )
        return false;
      Workout workout1 = (Workout) event1;
      Workout workout2 = (Workout) event2;
      return workout1.caloriesBurned == workout2.caloriesBurned;
    }

    public static void main( String[] args ) {
      Event event = new Workout();
      System.out.println( burnedSameCalories( event, event ) );
    }
  }

  static class Variant2 {
    static boolean burnedSameCalories( Event event1, Event event2 ) {
      if ( event1 instanceof Workout && event2 instanceof Workout ) {
        Workout workout1 = (Workout) event1;
        Workout workout2 = (Workout) event2;
        return workout1.caloriesBurned == workout2.caloriesBurned;
      }
      return false;
    }
  }

  static class Variant3 {
    static boolean burnedSameCalories( Event event1, Event event2 ) {
      if ( event1 instanceof Workout workout1 && event2 instanceof Workout workout2 )
        return workout1.caloriesBurned == workout2.caloriesBurned;
      return false;
    }
  }

  static class Variant4 {
    static boolean burnedSameCalories( Event event1, Event event2 ) {
      if ( !(event1 instanceof Workout workout1 && event2 instanceof Workout workout2) )
        return false;
      return workout1.caloriesBurned == workout2.caloriesBurned;
    }
  }

  static class Variant5 {
    static boolean burnedSameCalories( Event event1, Event event2 ) {
      return event1 instanceof Workout workout1 && event2 instanceof Workout workout2 &&
             workout1.caloriesBurned == workout2.caloriesBurned;
    }
  }
}
