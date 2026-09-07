package com.thor.lisboa.adapters.in.rest;

import com.thor.lisboa.adapters.in.mapper.EmailAdapterMapper;
import com.thor.lisboa.adapters.in.rest.swagger.EmailSwagger;
import com.thor.lisboa.application.service.EmailService;
import com.thor.lisboa.domain.mapper.EmailMapper;
import com.thor.lisboa.domain.response.email.EmailPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
public class EmailController implements EmailSwagger {

  private final EmailService service;

  @PostMapping(value = "/teste", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @Override
  public String teste(MultipartFile file) {
    return service.teste(file);
  }

  @Override
  public EmailPageResponse getByFilter(Integer size, String startCreatedDate, String endCreatedDate,
      String emailTypeId, String startSendDate, String endSendDate, String cursor,
      String direction) {
    var filter = EmailMapper.toFilter(startCreatedDate, endCreatedDate, emailTypeId, startSendDate,
        endSendDate, cursor, size, direction);
    var response = service.getByFilter(filter);
    return EmailAdapterMapper.toPageResponse(response);
  }
}
