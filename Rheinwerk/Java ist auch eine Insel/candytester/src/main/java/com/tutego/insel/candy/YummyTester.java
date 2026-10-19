package com.tutego.insel.candy;

import java.util.*;

public class YummyTester {
  public static boolean testYumminess( String name ) {
    return List.of( "ding ding tong", "meiji", "balikutsa", "pez", "gumballs" )
               .contains( name.toLowerCase( Locale.ROOT ) );
  }
}
