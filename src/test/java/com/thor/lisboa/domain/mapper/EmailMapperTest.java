package com.thor.lisboa.domain.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort.Direction;

class EmailMapperTest {

  @Test
  void mapsValidFilters() {
    String id = "6abae6828b683c8f9d982b91";
    String dateTime = "2026-09-28T15:30:45.000Z";

    var result = EmailMapper.toFilter(dateTime, dateTime, id, dateTime, dateTime,
        "cursor", 10, "ASC");

    assertEquals(Direction.ASC, result.getDirection());
    assertEquals("cursor", result.getCursor());
    assertEquals(10, result.getSize());
    assertEquals(new ObjectId(id), result.getEmailTypeId());
    assertEquals(LocalDateTime.of(2026, 9, 28, 15, 30, 45), result.getStartCreatedDate());
    assertEquals(LocalDateTime.of(2026, 9, 28, 15, 30, 45), result.getEndCreatedDate());
    assertEquals(LocalDateTime.of(2026, 9, 28, 15, 30, 45), result.getStartSendDate());
    assertEquals(LocalDateTime.of(2026, 9, 28, 15, 30, 45), result.getEndSendDate());
  }

  @Test
  void ignoresInvalidOptionalFilters() {
    var result = EmailMapper.toFilter("invalid", "invalid", "invalid-id", "invalid", "2026-09-28",
        null, null, "DESC");

    assertEquals(Direction.DESC, result.getDirection());
    assertNull(result.getEmailTypeId());
    assertNull(result.getStartCreatedDate());
    assertNull(result.getEndCreatedDate());
    assertNull(result.getStartSendDate());
    assertNull(result.getEndSendDate());
  }

  @Test
  void rejectsAnInvalidDirection() {
    assertThrows(IllegalArgumentException.class,
        () -> EmailMapper.toFilter(null, null, null, null, null, null, null, "INVALID"));
  }
}
