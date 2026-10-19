public class NullCheckForStringLength {

  public static void main( String[] args ) {
    String s = javax.swing.JOptionPane.showInputDialog( "Input a string" );
    if ( s != null && ! s.isEmpty() )
      System.out.println( "Length of string: " + s.length() );
    else
      System.out.println( "Dialog cancelled or no input given" );
  }
}
