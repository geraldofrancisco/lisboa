package com.thor.lisboa.adapters.out.integration;

import com.thor.lisboa.domain.dto.email.EmailFilterDTO;
import com.thor.lisboa.domain.repository.integration.email.response.EmailPageDTO;

public interface EmailIntegration {

  EmailPageDTO getByFilter(EmailFilterDTO filter);
}
