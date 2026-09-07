package com.thor.lisboa.adapters.in.rest;

import com.thor.lisboa.adapters.in.mapper.EmailAdapterMapper;
import com.thor.lisboa.adapters.in.rest.swagger.EmailSwagger;
import com.thor.lisboa.application.service.EmailService;
import com.thor.lisboa.domain.mapper.EmailMapper;
import com.thor.lisboa.domain.request.validation.ValueOfEnum;
import com.thor.lisboa.domain.response.email.EmailPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
public class EmailController implements EmailSwagger {

  private final EmailService service;

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @Override
  public String create(MultipartFile file) {
    return service.create(file);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Override
  public EmailPageResponse getByFilter(Integer size, String startCreatedDate, String endCreatedDate,
      String emailTypeId, String startSendDate, String endSendDate, String cursor,
      @RequestParam(required = false, defaultValue = "DESC")
      //@ValueOfEnum(enumClass = Direction.class)
      String direction) {
    var filter = EmailMapper.toFilter(startCreatedDate, endCreatedDate, emailTypeId, startSendDate,
        endSendDate, cursor, size, direction);
    var response = service.getByFilter(filter);
    return EmailAdapterMapper.toPageResponse(response);
  }
}
