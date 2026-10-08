package com.tutego.insel.game.c.vl;

public class PatternMatchingSwitch {

  //  static String toXml( Object o ) {
  //    if ( o == null )
  //      return "<null />";
  //    if ( o instanceof Nap nap )
  //      return "<nap about=\"%s\" duration=%s />".formatted( nap.about, nap.duration );
  //    else if ( o instanceof Workout workout )
  //      return "<workout about=\"%s\" duration=%s caloriesBurned=%s/> "
  //          .formatted( workout.about, workout.duration,
  //                      workout.caloriesBurned );
  //    else if ( o instanceof Event event )
  //      return "<event about=\"%s\" duration=%s />".formatted( event.about, event.duration );
  //    else
  //      return "<object />";
  //  }

  //  static String toXml( Object o ) {
  //    switch ( o ) {
  //      case null -> {
  //        return "<null />";
  //      }
  //      case Nap nap -> {
  //        return "<nap about=\"%s\" duration=%s />".formatted( nap.about, nap.duration );
  //      }
  //      case Workout workout -> {
  //        return "<workout about=\"%s\" duration=%s caloriesBurned=%s/> "
  //            .formatted( workout.about, workout.duration,
  //                        workout.caloriesBurned );
  //      }
  //      case Event event -> {
  //        return "<event about=\"%s\" duration=%s />".formatted( event.about, event.duration );
  //      }
  //      default -> {
  //        return "<object />";
  //      }
  //    }
  //  }

  static String toXml( Object o ) {
    return switch ( o ) {
      case null -> "<null />";
      case Nap nap ->
          "<nap about=\"%s\" duration=%s />".formatted( nap.about, nap.duration );
      case Workout workout ->
          "<workout about=\"%s\" duration=%s caloriesBurned=%s/> "
            .formatted( workout.about, workout.duration,
                        workout.caloriesBurned );
      case Event event ->
          "<event about=\"%s\" duration=%s />".formatted( event.about, event.duration );
      default -> "<object />";
    };
  }

  public static void main( String[] args ) {
    System.out.println( toXml( new Nap() ) );
    System.out.println( toXml( new Event() ) );
    System.out.println( toXml( new Workout() ) );
    System.out.println( toXml( "" ) );
  }
}
