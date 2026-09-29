package com.thor.lisboa.domain.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class PageMapperTest {

  @Test
  void canBeExtendedByConcreteMappers() {
    var mapper = new PageMapper() {};

    assertNotNull(mapper);
  }
}
