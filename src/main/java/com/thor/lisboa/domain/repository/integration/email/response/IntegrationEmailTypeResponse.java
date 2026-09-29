package com.thor.lisboa.domain.repository.integration.email.response;

import static com.thor.lisboa.domain.constants.ProjectConstants.DATE_TIME_PATTERN;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IntegrationEmailTypeResponse {

  private String id;

  @JsonFormat(pattern = DATE_TIME_PATTERN)
  private LocalDateTime timestampCreatedDate;

  private String body;

  private String name;
}
