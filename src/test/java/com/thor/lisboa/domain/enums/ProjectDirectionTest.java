package com.thor.lisboa.domain.enums;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class ProjectDirectionTest {

  @Test
  void exposesBothSupportedDirections() {
    assertArrayEquals(new ProjectDirection[] {ProjectDirection.ASC, ProjectDirection.DESC},
        ProjectDirection.values());
  }
}
