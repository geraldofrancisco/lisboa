package com.thor.lisboa.adapters.out.integration.impl;

import com.thor.lisboa.adapters.out.integration.AgentIntegration;
import com.thor.lisboa.adapters.out.integration.properties.AgentIntegrationProperties;
import com.thor.lisboa.domain.dto.agent.AgentIntegrationQuestRequest;
import com.thor.lisboa.domain.dto.agent.AgentIntegrationResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AgentIntegrationImpl extends BaseIntegration implements AgentIntegration {

  private final AgentIntegrationProperties properties;

  public AgentIntegrationImpl(AgentIntegrationProperties properties) {
    this.properties = properties;
    super(properties.getUrl(), properties.getConnectTimeout(), properties.getReadTimeout());
  }

  @Override
  public AgentIntegrationResponseDTO question(AgentIntegrationQuestRequest question) {
    return this.handleClientErrors(client.post()
        .uri(properties.getV1BaseUri())
        .body(question)
        .retrieve())
        .body(AgentIntegrationResponseDTO.class);
  }
}
