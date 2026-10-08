package com.tutego.insel.junit.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class StringsTest {

  @DisplayName( "reverse(string)" ) @Nested class reverse {

    @Test @DisplayName( "reverse a non null string" ) void reverse_non_null_string() {
      assertEquals( "", Strings.reverse( "" ) );
      assertEquals( "cba", Strings.reverse( "abc" ) );
    }

    @Test void reverse_null_string_1() {
      try {
        Strings.reverse( null );
        fail( "reverse(null) should throw IllegalArgumentException" );
      }
      catch ( IllegalArgumentException e ) { /* Ignore */ }
    }

    @Test void reverse_null_string_2() {
      assertThrows( IllegalArgumentException.class, () ->
        Strings.reverse( null )
      );
    }

    @Test void reverse_execution_time_below_1ms() {
      assertTimeout( Duration.ofMillis(1), () -> {
        Strings.reverse( "abc" );
      } );
    }
  }

  @ParameterizedTest
  @ValueSource( strings = { "", "  ", "abc"} )
  void reverse_will_not_throw_exception_with_non_null_inputs( String input ) {
    Strings.reverse( input );
  }

  @ParameterizedTest
  @CsvSource( { "a,a", "ab,ba", "abc,cba"} )
  void reverse_non_null_inputs( String input, String expected ) {
    assertEquals( expected, Strings.reverse( input ) );
  }
}
