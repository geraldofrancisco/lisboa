package com.thor.lisboa.domain.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.thor.lisboa.domain.response.exception.ExceptionFieldResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ExceptionMapperTest {

  @Test
  void mapsMessageAndStatusToResponse() {
    var response = ExceptionMapper.toResponse(HttpStatus.NOT_FOUND, "missing");

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("missing", response.getBody().getErrorDescription());
    assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getStatus());
    assertNull(response.getBody().getFields());
  }

  @Test
  void mapsFieldErrorsAndStatusToResponse() {
    var fields = List.of(ExceptionFieldResponse.builder().name("name").message("required").build());

    var response = ExceptionMapper.toResponse(HttpStatus.BAD_REQUEST, fields);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNull(response.getBody().getErrorDescription());
    assertEquals(fields, response.getBody().getFields());
  }
}
