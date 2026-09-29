package com.thor.lisboa.domain.repository.integration.email.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IntegrationEmailCreateResponse {

  private String id;
  private String body;
}
