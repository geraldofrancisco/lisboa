package com.thor.lisboa.adapters.out.integration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.integration.email")
@Data
public class EmailIntegrationProperties {
  private String url;
  private String v1BaseUri;
}
