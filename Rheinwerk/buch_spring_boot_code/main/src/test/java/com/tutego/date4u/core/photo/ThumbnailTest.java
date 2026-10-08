package com.tutego.date4u.core.photo;

import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ThumbnailTest {

  private static final byte[] MINIMAL_JPG = Base64.getDecoder().decode(
      "/9j/4AAQSkZJRgABAQEASABIAAD/2wBDAP////////////////////////////////////"
      +"//////////////////////////////////////////////////wgALCAABAAEBAREA/8QA"
      +"FBABAAAAAAAAAAAAAAAAAAAAAP/aAAgBAQABPxA=" );

  @Test
  void valid_jpeg_returns_non_null_bytes() {
    Thumbnail thumbnail = new Thumbnail();
    byte[] retVal = thumbnail.createThumbnail( MINIMAL_JPG );

    // JUnit
    assertNotNull( retVal );

    // AssertJ
    assertThat( retVal ).isNotNull();
  }

  @Test
  void null_input_throws_NPE() {
    Thumbnail thumbnail = new Thumbnail();

    // JUnit
    assertThrows( NullPointerException.class, () -> thumbnail.createThumbnail( null ) );

    // AssertJ
    assertThatThrownBy( () -> thumbnail.createThumbnail( null ) )
        .isInstanceOf( NullPointerException.class );
  }
}