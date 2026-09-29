package com.thor.lisboa.application.service;

import com.thor.lisboa.adapters.out.integration.AgentIntegration;
import com.thor.lisboa.adapters.out.integration.EmailIntegration;
import com.thor.lisboa.application.usecase.CreateEmailHtmlByAgentUseCase;
import com.thor.lisboa.application.usecase.ExcelReadSheetOnlyTextUseCase;
import com.thor.lisboa.domain.dto.email.EmailFilterDTO;
import com.thor.lisboa.domain.dto.pagination.PageDTO;
import com.thor.lisboa.domain.repository.integration.email.response.IntegrationEmailResponse;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class EmailService {

  private final ExcelReadSheetOnlyTextUseCase excelReadUseCase;

  private final EmailIntegration emailIntegration;
  private final CreateEmailHtmlByAgentUseCase htmlByAgentUseCase;


  public String create(MultipartFile file, String id) {
    var excel =  excelReadUseCase.execute(file);
    var email = emailIntegration.getEmailTypeById(id).getBody();
    var pair = Pair.of(excel, email);
    return htmlByAgentUseCase.execute(pair);
  }

  public PageDTO<IntegrationEmailResponse> getByFilter(EmailFilterDTO filter) {
    return emailIntegration.getByFilter(filter);
  }
}
