package com.thor.lisboa.adapters.out.integration.impl;

import com.thor.lisboa.adapters.out.integration.EmailIntegration;
import com.thor.lisboa.adapters.out.integration.properties.EmailIntegrationProperties;
import com.thor.lisboa.domain.dto.email.EmailFilterDTO;
import com.thor.lisboa.domain.exception.ProjectIntegrationException;
import com.thor.lisboa.domain.repository.integration.email.response.EmailPageDTO;
import com.thor.lisboa.domain.repository.integration.email.response.IntegrationEmailTypeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
public class EmailIntegrationImpl extends BaseIntegration implements EmailIntegration {

  private final EmailIntegrationProperties properties;

  public EmailIntegrationImpl(EmailIntegrationProperties properties) {
    this.properties = properties;
    super(properties.getUrl());
  }

  @Override
  public EmailPageDTO getByFilter(EmailFilterDTO filter) {
    return this.handleClientErrors(client.get()
            .uri(properties.getEmailV1Uri())
            .retrieve())
        .body(EmailPageDTO.class);
  }

  @Override
  public IntegrationEmailTypeResponse getEmailTypeById(String id) {
    var url = UriComponentsBuilder.fromPath(properties.getTypeEmailV1Uri())
        .pathSegment(id)
        .toUriString();
    return this.handleClientErrors(client.get()
            .uri(url)
            .retrieve())
        .body(IntegrationEmailTypeResponse.class);
  }
}
