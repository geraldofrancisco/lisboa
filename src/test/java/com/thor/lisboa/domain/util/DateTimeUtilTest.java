package com.thor.lisboa.domain.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DateTimeUtilTest {

  @Test
  void validatesAndParsesDates() {
    assertTrue(DateTimeUtil.validateDate(null));
    assertTrue(DateTimeUtil.validateDate(""));
    assertTrue(DateTimeUtil.validateDate("2026-09-28"));
    assertFalse(DateTimeUtil.validateDate("2026-02-30"));
    assertFalse(DateTimeUtil.validateDate("2026-9-28"));
    assertEquals(LocalDate.of(2026, 9, 28), DateTimeUtil.toLocalDate("2026-09-28"));
    assertNull(DateTimeUtil.toLocalDate(" "));
  }

  @Test
  void validatesAndParsesDateTimes() {
    String dateTime = "2026-09-28T15:30:45.000Z";

    assertTrue(DateTimeUtil.validateDateTime(null));
    assertTrue(DateTimeUtil.validateDateTime(dateTime));
    assertFalse(DateTimeUtil.validateDateTime("2026-02-30T15:30:45.000Z"));
    assertFalse(DateTimeUtil.validateDateTime("2026-09-28"));
    assertEquals(LocalDateTime.of(2026, 9, 28, 15, 30, 45),
        DateTimeUtil.toLocalDateTime(dateTime));
    assertNull(DateTimeUtil.toLocalDateTime(" "));
  }
}
