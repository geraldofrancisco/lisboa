package com.thor.lisboa.adapters.in.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.thor.lisboa.domain.repository.integration.email.response.EmailPageDTO;
import com.thor.lisboa.domain.repository.integration.email.response.IntegrationEmailResponse;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;

class EmailAdapterMapperTest {

  @Test
  void mapsAllPageAndEmailFields() {
    var created = LocalDateTime.of(2026, 9, 28, 15, 30);
    var sent = LocalDateTime.of(2026, 9, 29, 15, 30);
    var email = IntegrationEmailResponse.builder()
        .id("id")
        .emailTypeId("type")
        .timestampCreatedDate(created)
        .title("title")
        .body("body")
        .to(List.of("to@example.com"))
        .bcc(List.of("bcc@example.com"))
        .timestampSendDate(sent)
        .build();
    var page = new EmailPageDTO();
    page.setContent(List.of(email));
    page.setHasNext(true);
    page.setNextPosition("next");

    var result = EmailAdapterMapper.toPageResponse(page);

    assertEquals(true, result.getHasNext());
    assertEquals("next", result.getNextPosition());
    assertEquals(1, result.getContent().size());
    var mapped = result.getContent().getFirst();
    assertEquals("id", mapped.getId());
    assertEquals("type", mapped.getEmailTypeId());
    assertEquals(created, mapped.getTimestampCreatedDate());
    assertEquals("title", mapped.getTitle());
    assertEquals("body", mapped.getBody());
    assertEquals(List.of("to@example.com"), mapped.getTo());
    assertEquals(List.of("bcc@example.com"), mapped.getBcc());
    assertEquals(sent, mapped.getTimestampSendDate());
  }
}
