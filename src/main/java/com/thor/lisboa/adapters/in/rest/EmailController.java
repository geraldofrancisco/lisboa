package com.thor.lisboa.adapters.in.rest;

import com.thor.lisboa.adapters.in.rest.swagger.EmailSwagger;
import com.thor.lisboa.application.service.EmailService;
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
}
