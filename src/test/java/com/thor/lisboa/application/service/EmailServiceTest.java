package com.thor.lisboa.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.thor.lisboa.adapters.out.integration.EmailIntegration;
import com.thor.lisboa.application.usecase.CreateEmailHtmlByAgentUseCase;
import com.thor.lisboa.application.usecase.ExcelReadSheetOnlyTextUseCase;
import com.thor.lisboa.domain.dto.email.EmailFilterDTO;
import com.thor.lisboa.domain.repository.integration.email.response.EmailPageDTO;
import com.thor.lisboa.domain.repository.integration.email.response.IntegrationEmailTypeResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

class EmailServiceTest {

  private final ExcelReadSheetOnlyTextUseCase excel = mock(ExcelReadSheetOnlyTextUseCase.class);
  private final EmailIntegration emailIntegration = mock(EmailIntegration.class);
  private final CreateEmailHtmlByAgentUseCase htmlUseCase =
      mock(CreateEmailHtmlByAgentUseCase.class);
  private final EmailService service = new EmailService(excel, emailIntegration, htmlUseCase);

  @Test
  void createsEmailFromSpreadsheetAndTemplate() {
    var file = new MockMultipartFile("file", "email.xlsx", "application/octet-stream", new byte[0]);
    when(excel.execute(file)).thenReturn("spreadsheet");
    when(emailIntegration.getEmailTypeById("6abae6828b683c8f9d982b91"))
        .thenReturn(IntegrationEmailTypeResponse.builder().body("template").build());
    when(htmlUseCase.execute(org.mockito.ArgumentMatchers.any())).thenReturn("<html/>");

    assertEquals("<html/>", service.create(file, "6abae6828b683c8f9d982b91"));
    verify(htmlUseCase).execute(org.mockito.ArgumentMatchers.argThat(
        pair -> "spreadsheet".equals(pair.getFirst()) && "template".equals(pair.getSecond())));
  }

  @Test
  void getsFilteredEmailsFromIntegration() {
    var filter = EmailFilterDTO.builder().cursor("cursor").build();
    var page = new EmailPageDTO();
    page.setContent(List.of());
    page.setHasNext(false);
    when(emailIntegration.getByFilter(filter)).thenReturn(page);

    assertEquals(page, service.getByFilter(filter));
    verify(emailIntegration).getByFilter(filter);
  }
}
