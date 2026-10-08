public class SimpleYearMonthParser {

  @SuppressWarnings( "resource" )
  public static void main( String[] args ) {

    String date = "24 12";

    int month = 0, year = 0;
    java.util.Scanner scanner = new java.util.Scanner( date );

    switch ( date.length() ) {
      case 5:  // YY MM
        year = 2000;
        // Fall-through
      case 7: // YYYY MM
        year += scanner.nextInt();
        // Fall-through
      case 2: // MM
        month = scanner.nextInt();
        if ( year == 0 )
          year = java.time.Year.now().getValue();
        break;
      default :
        System.err.println( "Wrong format" );
    }

    System.out.println( "Month=" + month + ", Year=" + year );
  }
}