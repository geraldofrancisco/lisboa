package com.thor.lisboa.domain.repository.integration.email.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IntegrationEmailCreateRequest {
  private String body;
}
