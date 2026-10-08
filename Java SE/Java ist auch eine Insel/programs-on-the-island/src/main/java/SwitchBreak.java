public class SwitchBreak {

  public static void main( String[] args ) {
    // cytosine [C], guanine [G], adenine [A] or thymine [T]
    String dnaBases = "CGCAGTTCTTCGGXAC";
    int a = 0, g = 0, c = 0, t = 0;

    loop:
    for ( int i = 0; i < dnaBases.length(); i++ ) {
      switch ( dnaBases.charAt( i ) ) {
        case 'A', 'a':
          a++;
          break;
        case 'C', 'c':
          c++;
          break;
        case 'G', 'g':
          g++;
          break;
        case 'T', 't':
          t++;
          break;
        default:
          System.err.println( "Unknown nucleotide " + dnaBases.charAt( i ) );
          break loop;
      }
    }

    System.out.printf( "Total: A=%d, G=%d, C=%d, T=%d%n", a, g, c, t );
  }
}