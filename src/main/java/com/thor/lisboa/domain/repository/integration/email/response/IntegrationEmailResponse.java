package com.thor.lisboa.domain.repository.integration.email.response;

import static com.thor.lisboa.domain.constants.ProjectConstants.DATE_TIME_PATTERN;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IntegrationEmailResponse {
  private String id;
  private String emailTypeId;
  @JsonFormat(pattern = DATE_TIME_PATTERN)
  private LocalDateTime timestampCreatedDate;
  private String title;
  private String body;
  private List<String> to;
  private List<String> bcc;
  @JsonFormat(pattern = DATE_TIME_PATTERN)
  private LocalDateTime timestampSendDate;
}
