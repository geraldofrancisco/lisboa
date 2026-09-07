package com.thor.lisboa.adapters.out.integration.impl;

import com.thor.lisboa.adapters.out.integration.AgentIntegration;
import com.thor.lisboa.domain.dto.agent.AgentIntegrationResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AgentIntegrationImpl implements AgentIntegration {

  @Override
  public AgentIntegrationResponseDTO question(String question) {
    return null;
  }
}
