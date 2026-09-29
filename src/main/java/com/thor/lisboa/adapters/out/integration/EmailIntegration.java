package com.thor.lisboa.adapters.out.integration;

import com.thor.lisboa.domain.dto.email.EmailFilterDTO;
import com.thor.lisboa.domain.repository.integration.email.response.EmailPageDTO;
import com.thor.lisboa.domain.repository.integration.email.response.IntegrationEmailResponse;
import com.thor.lisboa.domain.repository.integration.email.response.IntegrationEmailTypeResponse;

public interface EmailIntegration {

  EmailPageDTO getByFilter(EmailFilterDTO filter);

  IntegrationEmailTypeResponse getEmailTypeById(String id);
}
