package com.thor.lisboa.application.service;

import com.thor.lisboa.adapters.out.integration.EmailIntegration;
import com.thor.lisboa.application.usecase.CreateEmailHtmlByAgentUseCase;
import com.thor.lisboa.application.usecase.ExcelReadSheetOnlyTextUseCase;
import com.thor.lisboa.domain.dto.email.EmailFilterDTO;
import com.thor.lisboa.domain.dto.pagination.PageDTO;
import com.thor.lisboa.domain.mapper.EmailMapper;
import com.thor.lisboa.domain.repository.integration.email.response.IntegrationEmailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class EmailService {

  private final ExcelReadSheetOnlyTextUseCase excelReadUseCase;
  private final CreateEmailHtmlByAgentUseCase htmlByAgentUseCase;
  private final EmailIntegration emailIntegration;

  public String create(MultipartFile file, String id) {
    var excel = excelReadUseCase.execute(file);
    var email = emailIntegration.getEmailTypeById(id).getBody();
    var html = htmlByAgentUseCase.execute(Pair.of(excel, email));
    return emailIntegration.create(EmailMapper.toCreateRequest(html)).getId();
  }

  public PageDTO<IntegrationEmailResponse> getByFilter(EmailFilterDTO filter) {
    return emailIntegration.getByFilter(filter);
  }
}
