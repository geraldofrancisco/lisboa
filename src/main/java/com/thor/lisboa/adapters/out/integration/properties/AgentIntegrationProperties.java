package com.thor.lisboa.adapters.out.integration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.integration.agent")
@Data
public class AgentIntegrationProperties {

  private String url;
  private String v1BaseUri;
}
