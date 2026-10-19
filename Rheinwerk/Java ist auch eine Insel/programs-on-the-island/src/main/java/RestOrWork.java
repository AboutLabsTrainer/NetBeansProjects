
public class RestOrWork {

  public static void main( String[] args ) {

    int hour = 12;

    switch ( hour ) {
      // Nachtruhe von 22 Uhr bis 6 Uhr
      case 22:
      case 23:
      case 24: case 0:
      case 1: 
      case 2: 
      case 3: 
      case 4:
      case 5: 
        System.out.println( "Nachtruhe" );
        break;

      // Mittagsruhe von 13 bis 15 Uhr
      case 13:
      case 14:  
        System.out.println( "Mittagsruhe" );
        break;

      default :
        System.out.println( "Arbeiten" );
        break;
    }
  }
}
