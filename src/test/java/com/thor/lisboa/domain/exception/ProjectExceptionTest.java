package com.thor.lisboa.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ProjectExceptionTest {

  @Test
  void businessExceptionUsesUnprocessableEntityStatus() {
    var exception = new ProjectBusinessException("business");

    assertEquals("business", exception.getMessage());
    assertEquals(HttpStatus.UNPROCESSABLE_CONTENT, exception.getStatus());
    assertNull(exception.getE());
  }

  @Test
  void integrationExceptionUsesTheProvidedStatus() {
    var exception = new ProjectIntegrationException("integration", HttpStatus.BAD_GATEWAY);

    assertEquals("integration", exception.getMessage());
    assertEquals(HttpStatus.BAD_GATEWAY, exception.getStatus());
    assertNull(exception.getE());
  }

  @Test
  void notFoundExceptionUsesNotFoundStatus() {
    var exception = new ProjectNotFoundException("missing");

    assertEquals("missing", exception.getMessage());
    assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    assertNull(exception.getE());
  }
}
