package com.thor.lisboa.adapters.out.integration.impl;

import com.thor.lisboa.adapters.out.integration.EmailIntegration;
import com.thor.lisboa.adapters.out.integration.properties.EmailIntegrationProperties;
import com.thor.lisboa.domain.dto.email.EmailFilterDTO;
import com.thor.lisboa.domain.exception.ProjectIntegrationException;
import com.thor.lisboa.domain.repository.integration.email.response.EmailPageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClient;

@Repository
public class EmailIntegrationImpl implements EmailIntegration {

  private final EmailIntegrationProperties properties;
  private final RestClient client;

  public EmailIntegrationImpl(EmailIntegrationProperties properties) {
    this.properties = properties;
    client = RestClient.builder()
        .baseUrl(properties.getUrl())
        .build();
  }

  @Override
  public EmailPageDTO getByFilter(EmailFilterDTO filter) {
    return client.get()
        .uri(properties.getV1BaseUri())
        .retrieve()
        .onStatus(HttpStatusCode::is4xxClientError, (_, response) -> {
          String exception = new String(response.getBody().readAllBytes());
          throw new ProjectIntegrationException(exception,
              HttpStatus.valueOf(response.getStatusCode().value()));
        })
        .body(EmailPageDTO.class);
  }
}
