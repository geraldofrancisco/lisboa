package com.thor.lisboa.adapters.out.integration;

import com.thor.lisboa.domain.dto.agent.AgentIntegrationResponseDTO;

public interface AgentIntegration {
  AgentIntegrationResponseDTO question(String question);
}
