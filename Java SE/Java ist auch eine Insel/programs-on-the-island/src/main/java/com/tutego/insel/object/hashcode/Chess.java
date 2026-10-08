package com.tutego.insel.object.hashcode;

import java.util.Arrays;

public class Chess {

  char[][] chessboard = new char[8][8];

  @Override public boolean equals( Object o ) {
    if ( this == o )
      return true;
    if ( !(o instanceof Chess chess) )
      return false;

    return Arrays.deepEquals( chessboard, chess.chessboard );
  }

  @Override public int hashCode() {
    return Arrays.deepHashCode( chessboard );
  }
}
