package com.thor.lisboa.adapters.out.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;

import com.thor.lisboa.adapters.out.integration.AgentIntegration;
import com.thor.lisboa.adapters.out.integration.properties.AgentIntegrationProperties;
import com.thor.lisboa.adapters.out.integration.properties.EmailIntegrationProperties;
import com.thor.lisboa.application.usecase.CreateEmailHtmlByAgentUseCase;
import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class ConfigurationTest {

  @Test
  void createsTheConfiguredAgentUseCase() {
    var integration = mock(AgentIntegration.class);

    var useCase = new UseCaseConfiguration().createEmailHtmlByAgentUseCase(integration);

    assertInstanceOf(CreateEmailHtmlByAgentUseCase.class, useCase);
  }

  @Test
  void createsOpenApiMetadataWithTheApplicationVersion() {
    var configuration = new OpenApiConfiguration();
    ReflectionTestUtils.setField(configuration, "appVersion", "1.2.3");

    OpenAPI openApi = configuration.customOpenAPI();

    assertEquals("Lisboa App", openApi.getInfo().getTitle());
    assertEquals("1.2.3", openApi.getInfo().getVersion());
    assertEquals("Backend For Frontend - Lisboa Distribuidora de Livros LTDA",
        openApi.getInfo().getDescription());
  }

  @Test
  void integrationPropertiesExposeConfiguredValues() {
    var agent = new AgentIntegrationProperties();
    agent.setUrl("http://agent");
    agent.setV1BaseUri("/question");
    var email = new EmailIntegrationProperties();
    email.setUrl("http://email");
    email.setEmailV1Uri("/emails");
    email.setTypeEmailV1Uri("/types");

    assertEquals("http://agent", agent.getUrl());
    assertEquals("/question", agent.getV1BaseUri());
    assertEquals("http://email", email.getUrl());
    assertEquals("/emails", email.getEmailV1Uri());
    assertEquals("/types", email.getTypeEmailV1Uri());
  }
}
