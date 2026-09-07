package com.thor.lisboa.adapters.in.mapper;


import com.thor.lisboa.domain.dto.pagination.PageDTO;
import com.thor.lisboa.domain.repository.integration.email.response.IntegrationEmailResponse;
import com.thor.lisboa.domain.response.email.EmailPageResponse;
import com.thor.lisboa.domain.response.email.EmailResponse;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailAdapterMapper {


  public static EmailPageResponse toPageResponse(PageDTO<IntegrationEmailResponse> dto) {
    return EmailPageResponse.builder()
        .content(EmailAdapterMapper.toListResponse(dto.getContent()))
        .hasNext(dto.getHasNext())
        .nextPosition(dto.getNextPosition())
        .build();
  }

  private static List<EmailResponse> toListResponse(List<IntegrationEmailResponse> list) {
    return list.parallelStream()
        .map(EmailAdapterMapper::toResponse)
        .toList();
  }

  private static EmailResponse toResponse(IntegrationEmailResponse dto) {
    return EmailResponse.builder()
        .id(dto.getId())
        .emailTypeId(dto.getEmailTypeId())
        .timestampCreatedDate(dto.getTimestampCreatedDate())
        .title(dto.getTitle())
        .body(dto.getBody())
        .to(dto.getTo())
        .bcc(dto.getBcc())
        .timestampSendDate(dto.getTimestampSendDate())
        .build();
  }
}
