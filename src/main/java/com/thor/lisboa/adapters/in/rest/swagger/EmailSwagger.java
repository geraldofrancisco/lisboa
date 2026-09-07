package com.thor.lisboa.adapters.in.rest.swagger;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface EmailSwagger {

  String teste(@RequestParam("file") MultipartFile file);
}
