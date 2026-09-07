package com.thor.lisboa.application.service;

import com.thor.lisboa.adapters.out.integration.EmailIntegration;
import com.thor.lisboa.application.usecase.ExcelReadSheetOnlyTextUseCase;
import com.thor.lisboa.domain.dto.email.EmailFilterDTO;
import com.thor.lisboa.domain.dto.pagination.PageDTO;
import com.thor.lisboa.domain.repository.integration.email.response.IntegrationEmailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class EmailService {

  private final ExcelReadSheetOnlyTextUseCase excelRead;
  private final EmailIntegration emailIntegration;

  public String create(MultipartFile file) {
    return excelRead.read(file);
  }

  public PageDTO<IntegrationEmailResponse> getByFilter(EmailFilterDTO filter) {
    return emailIntegration.getByFilter(filter);
  }
}
