package com.tutego.insel.records;

record Point( int x, int y ) {}

record Line( Point start, Point end ) {}

class ZeroTester {
  private static boolean isZeroPoint( Object object ) {
    // return object instanceof Point && ((Point) object).x() == 0 && ((Point) object).y() == 0;
    // return object instanceof Point p && p.x() == 0 && p.y() == 0;
    // return object instanceof Point p && (p.x() | p.y()) == 0;
    return object instanceof Point( int a, int b ) && (a | b) == 0;
  }

  private static boolean isZeroLine( Object object ) {
    //    return    object instanceof Line line
    //           && (line.start().x() | line.start().y() | line.end().x() | line.end().y()) == 0;
    //    return object instanceof Line( Point start, Point end )
    //        && (start.x() | start.y() | end.x() | end.y()) == 0;
    return object instanceof Line( var start, var end )
        && (start.x() | start.y() | end.x() | end.y()) == 0;
//    return object instanceof Line(
//        Point( int x1, int y1 ), Point( int x2, int y2 )
//    ) && (x1 | y1 | x2 | y2) == 0;
  }

  private static boolean isZero( Object o ) {
    return switch ( o ) {
      // case Point p -> (p.x() | p.y()) == 0;
      // case Line l -> (l.start().x() | l.start().y() | l.end().x() | l.end().y()) == 0;
      case Point( int x, int y ) -> (x | y) == 0;
      case Line( Point s, Point e ) -> isZero( s ) && isZero( e );
      // case Line(Point(int x1, int y1), Point(int x2, int y2)) -> (x1 | y1 | x2 | y2) == 0;
//      case Line( Point( int x1, int y1 ), Point( int x2, int y2 ) ) when (x1 | y1 | x2 | y2) == 0 ->
//          true;
      default -> false;
    };
  }

  public static void main( String[] args ) {
    Point zero = new Point( 0, 0 );
    System.out.println( isZeroPoint( zero ) );
    System.out.println( isZeroLine( new Line( zero, zero ) ) );
    System.out.println( isZero( zero ) );
    System.out.println( isZero( new Line( zero, zero ) ) );
  }
}
