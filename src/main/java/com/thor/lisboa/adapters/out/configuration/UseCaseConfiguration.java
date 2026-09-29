package com.thor.lisboa.adapters.out.configuration;

import com.thor.lisboa.adapters.out.integration.AgentIntegration;
import com.thor.lisboa.application.usecase.CreateEmailHtmlByAgentUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

  @Bean
  public CreateEmailHtmlByAgentUseCase createEmailHtmlByAgentUseCase(
      AgentIntegration agentIntegration) {
    return new CreateEmailHtmlByAgentUseCase(agentIntegration);
  }
}
