public class SwitchArrow {
  public static void main( String[] args ) {
    int x = 120, y = 2;
    String operator = "+";
    double result = switch ( operator ) {
      case "+" -> x + y;
      case "-" -> x - y;
      case "*" -> x * y;
      case "/" -> {
        if ( y == 0 )
          yield -1;
        yield x / y;
      }
      default -> throw new IllegalStateException( "Unexpected operator: " + operator );
    };
  }
}
