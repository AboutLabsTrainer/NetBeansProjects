package com.tutego.insel.assertj;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertJDemo {

  @Test public void test1() {
    assertThat( new Object() ).isNotNull();
    assertThat( "" ).isEqualTo( "" );
  }

  @Test public void test2() {
    List<String> letters = new ArrayList<>();
    Collections.addAll( letters, "a", "b", "c", "d", "e" );

    letters.removeAll( Arrays.asList( "b", "d" ) );

    assertThat( letters ).contains( "a" ).contains( "c" );

    assertThat( letters ).hasSize( 3 );
    assertThat( letters ).contains( "a", "c", "e" ).doesNotContain( "b", "d" );
  }
}
