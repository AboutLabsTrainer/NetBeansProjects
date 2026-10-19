public class AutoConvert {

  @SuppressWarnings( "unused" )
  public static void main( String[] args ) {
    int   int1  = 1, int2 = 2;
    int   int3  = int1 + int2;
    long  long1 = 1, long2 = 2;
    long  long3 = long1 + long2;

    short short1 = 1, short2 = 2;
    byte  byte1  = 1, byte2 = 2;

//    short short3 = short1 + short2;  // Type mismatch: cannot convert from int to short
//    byte  byte3 = byte1 + byte2;  // Type mismatch: cannot convert from int to byte

    short short3 = (short)( short1 + short2 );
    byte  byte3  = (byte)( byte1 + byte2 );
  }
}