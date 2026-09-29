package com.thor.lisboa.adapters.out.integration.impl;

import com.thor.lisboa.domain.exception.ProjectIntegrationException;
import com.thor.lisboa.domain.response.exception.ExceptionResponse;
import com.thor.lisboa.domain.util.JsonUtil;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

public abstract class BaseIntegration {

  protected final RestClient client;

  protected BaseIntegration(String baseUrl) {
    SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
    requestFactory.setConnectTimeout(Duration.ofSeconds(3));
    requestFactory.setReadTimeout(Duration.ofSeconds(5));

    this.client = RestClient.builder()
        .baseUrl(baseUrl)
        .requestFactory(requestFactory)
        .build();
  }

  protected RestClient.ResponseSpec handleClientErrors(RestClient.ResponseSpec responseSpec) {
    return responseSpec.onStatus(
        status -> status.value() == HttpStatus.BAD_REQUEST.value()
            || status.value() == HttpStatus.INTERNAL_SERVER_ERROR.value(),
        (_, response) -> {
          String message = new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8);
          var error = JsonUtil.fromJson(message, ExceptionResponse.class);
          throw new ProjectIntegrationException(
              error.getErrorDescription(),
              HttpStatus.valueOf(response.getStatusCode().value()));
        });
  }
}
