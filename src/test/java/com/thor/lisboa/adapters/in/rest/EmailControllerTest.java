package com.thor.lisboa.adapters.in.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.thor.lisboa.application.service.EmailService;
import com.thor.lisboa.domain.repository.integration.email.response.EmailPageDTO;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

class EmailControllerTest {

  private final EmailService service = mock(EmailService.class);
  private final EmailController controller = new EmailController(service);

  @Test
  void createsAnEmailFromAnUploadedFile() {
    var file = new MockMultipartFile("file", "email.xlsx", "application/octet-stream", new byte[0]);
    when(service.create(file, "6abae6828b683c8f9d982b91")).thenReturn("<html/>");

    assertEquals("<html/>", controller.create(file));
    verify(service).create(file, "6abae6828b683c8f9d982b91");
  }

  @Test
  void mapsFilterParametersAndReturnsThePage() {
    var page = new EmailPageDTO();
    page.setContent(List.of());
    page.setHasNext(false);
    when(service.getByFilter(any())).thenReturn(page);

    var result = controller.getByFilter(10, null, null, null, null, null, "DESC", "cursor");

    assertEquals(false, result.getHasNext());
    assertEquals(List.of(), result.getContent());
    verify(service).getByFilter(any());
  }
}
