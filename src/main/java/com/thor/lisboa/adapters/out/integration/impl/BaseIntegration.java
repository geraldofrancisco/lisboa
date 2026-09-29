package com.thor.lisboa.adapters.out.integration.impl;

import com.thor.lisboa.domain.exception.ProjectIntegrationException;
import com.thor.lisboa.domain.response.exception.ExceptionResponse;
import com.thor.lisboa.domain.util.JsonUtil;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

public abstract class BaseIntegration {

  protected final RestClient client;

  protected BaseIntegration(String baseUrl) {
    this(baseUrl, Duration.ofSeconds(3), Duration.ofSeconds(5));
  }

  protected BaseIntegration(String baseUrl, Duration connectTimeout, Duration readTimeout) {
    SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
    requestFactory.setConnectTimeout(connectTimeout);
    requestFactory.setReadTimeout(readTimeout);

    this.client = RestClient.builder()
        .baseUrl(baseUrl)
        .requestFactory(requestFactory)
        .build();
  }

  protected RestClient.ResponseSpec handleClientErrors(RestClient.ResponseSpec responseSpec) {
    return responseSpec.onStatus(
        status -> status.value() >= HttpStatus.BAD_REQUEST.value()
            && status.value() <= HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value(),
        (_, response) -> {
          String message = new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8);
          var error = JsonUtil.fromJson(message, ExceptionResponse.class);
          String errorDescription = error != null ? error.getErrorDescription() : message;
          throw new ProjectIntegrationException(
              errorDescription,
              HttpStatus.valueOf(response.getStatusCode().value()));
        });
  }
}
