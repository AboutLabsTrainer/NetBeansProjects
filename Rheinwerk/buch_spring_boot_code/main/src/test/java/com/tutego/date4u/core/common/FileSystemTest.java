package com.tutego.date4u.core.common;

import com.tutego.date4u.infrastructure.LocalFileSystem;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class FileSystemTest {

  @Test
  void store_and_load_successful() {
    // given
    FileSystem fileSystem = new LocalFileSystem();
    fileSystem.store( "test.txt", "Hello World".getBytes() );

    // when
    byte[] actual = fileSystem.load( "test.txt" );

    // then
    assertArrayEquals( "Hello World".getBytes(), actual, "Loaded bytes must equal stored bytes");
    assertThat( actual ).containsExactly( "Hello World".getBytes() );
  }

  @Test
  void loading_arbitrary_file_is_rejected() {
    FileSystem fileSystem = new LocalFileSystem();
    assertThatThrownBy( () -> {
      fileSystem.load( "../../../../../../../../../../Windows/notepad.exe" );
    } ).isInstanceOf( SecurityException.class )
        .hasMessageContaining( "Access to" )
        .hasMessageContaining( "denied" );
  }
}