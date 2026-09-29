package com.thor.lisboa.adapters.out.integration.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sun.net.httpserver.HttpServer;
import com.thor.lisboa.adapters.out.integration.properties.AgentIntegrationProperties;
import com.thor.lisboa.adapters.out.integration.properties.EmailIntegrationProperties;
import com.thor.lisboa.domain.dto.agent.AgentIntegrationQuestRequest;
import com.thor.lisboa.domain.dto.email.EmailFilterDTO;
import com.thor.lisboa.domain.exception.ProjectIntegrationException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IntegrationClientsTest {

  private final AtomicReference<StubResponse> response =
      new AtomicReference<>(new StubResponse(200, "{}"));
  private HttpServer server;
  private EmailIntegrationImpl emailIntegration;
  private AgentIntegrationImpl agentIntegration;

  @BeforeEach
  void startServer() throws Exception {
    server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
    server.createContext("/", exchange -> {
      var stub = response.get();
      byte[] body = stub.body().getBytes(StandardCharsets.UTF_8);
      exchange.getResponseHeaders().add("Content-Type", "application/json");
      exchange.sendResponseHeaders(stub.status(), body.length);
      try (var output = exchange.getResponseBody()) {
        output.write(body);
      }
    });
    server.start();

    String baseUrl = "http://127.0.0.1:" + server.getAddress().getPort();
    var emailProperties = new EmailIntegrationProperties();
    emailProperties.setUrl(baseUrl);
    emailProperties.setEmailV1Uri("/emails");
    emailProperties.setTypeEmailV1Uri("/types");
    emailIntegration = new EmailIntegrationImpl(emailProperties);

    var agentProperties = new AgentIntegrationProperties();
    agentProperties.setUrl(baseUrl);
    agentProperties.setV1BaseUri("/questions");
    agentIntegration = new AgentIntegrationImpl(agentProperties);
  }

  @AfterEach
  void stopServer() {
    server.stop(0);
  }

  @Test
  void retrievesEmailPagesAndTypes() {
    response.set(new StubResponse(200,
        "{\"content\":[],\"hasNext\":true,\"nextPosition\":\"next\"}"));
    var page = emailIntegration.getByFilter(EmailFilterDTO.builder().build());

    assertEquals(true, page.getHasNext());
    assertEquals("next", page.getNextPosition());

    response.set(new StubResponse(200, "{\"id\":\"type\",\"name\":\"notice\",\"body\":\"body\"}"));
    assertEquals("type", emailIntegration.getEmailTypeById("type").getId());
  }

  @Test
  void sendsAgentQuestionsAndReadsTheResponse() {
    response.set(new StubResponse(200, "{\"data\":\"answer\"}"));

    var result = agentIntegration.question(AgentIntegrationQuestRequest.builder()
        .question("question")
        .build());

    assertEquals("answer", result.getData());
  }

  @Test
  void translatesBadRequestAndServerErrors() {
    response.set(new StubResponse(400, "{\"error\":\"bad request\"}"));
    var badRequest = assertThrows(ProjectIntegrationException.class,
        () -> emailIntegration.getByFilter(EmailFilterDTO.builder().build()));
    assertEquals("bad request", badRequest.getMessage());
    assertEquals(org.springframework.http.HttpStatus.BAD_REQUEST, badRequest.getStatus());

    response.set(new StubResponse(500, "{\"error\":\"server error\"}"));
    var serverError = assertThrows(ProjectIntegrationException.class,
        () -> agentIntegration.question(AgentIntegrationQuestRequest.builder()
            .question("question").build()));
    assertEquals("server error", serverError.getMessage());
    assertEquals(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR, serverError.getStatus());
  }

  @Test
  void translatesAnyClientOrServerErrorStatus() {
    response.set(new StubResponse(404, "{\"error\":\"missing\"}"));
    var notFound = assertThrows(ProjectIntegrationException.class,
        () -> emailIntegration.getEmailTypeById("missing"));
    assertEquals("missing", notFound.getMessage());
    assertEquals(org.springframework.http.HttpStatus.NOT_FOUND, notFound.getStatus());
  }

  @Test
  void fallsBackToRawBodyWhenErrorPayloadIsNotJson() {
    response.set(new StubResponse(500, "internal failure"));

    var error = assertThrows(ProjectIntegrationException.class,
        () -> agentIntegration.question(AgentIntegrationQuestRequest.builder()
            .question("question").build()));

    assertEquals("internal failure", error.getMessage());
  }

  @Test
  void ignoresUnknownFieldsInTheErrorPayload() {
    response.set(new StubResponse(400,
        "{\"error\":\"bad request\",\"path\":\"/api/v1/question\"}"));

    var error = assertThrows(ProjectIntegrationException.class,
        () -> agentIntegration.question(AgentIntegrationQuestRequest.builder()
            .question("question").build()));

    assertEquals("bad request", error.getMessage());
  }

  private record StubResponse(int status, String body) {
  }
}
