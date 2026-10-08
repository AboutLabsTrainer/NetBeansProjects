package com.tutego.insel.junit.util;

import java.util.logging.Logger;
import org.junit.jupiter.api.*;

public class LifecycleDemo {

  static final Logger log = Logger.getLogger( LifecycleDemo.class.getName() );

  @BeforeAll
  public static void beforeClass() { log.info( "@BeforeAll" ); }

  @AfterAll
  public static void afterClass() { log.info( "@AfterClass" ); }

  @BeforeEach
  public void setUp() { log.info( "@Before" ); }

  @AfterEach
  public void tearDown() { log.info( "@After" ); }

  @Test
  public void test1() { log.info( "test 1" ); }

  @Test
  public void test2() { log.info( "test 2" ); }
}
