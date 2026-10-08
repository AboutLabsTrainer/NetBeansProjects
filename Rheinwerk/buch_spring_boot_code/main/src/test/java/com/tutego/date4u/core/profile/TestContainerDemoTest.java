package com.tutego.date4u.core.profile;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@AutoConfigureTestDatabase( replace = AutoConfigureTestDatabase.Replace.NONE )
@TestPropertySource( properties = "spring.jpa.hibernate.ddl-auto=create-drop" )
@TestPropertySource( properties = "spring.flyway.enabled=false" )
@Testcontainers
class TestContainerDemoTest {

  @Container
  @ServiceConnection
  static PostgreSQLContainer __ = new PostgreSQLContainer( "postgres:17.2" );

  @Autowired private ProfileRepository profiles;

  @Test
  void test_container_database_exists_but_is_empty() {
    Assertions.assertThat( profiles.count() ).isZero();
  }
}