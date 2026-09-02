package com.thor.lisboa.domain.exception;

import org.springframework.http.HttpStatus;

public class ProjectNotFoundException extends ProjectException {

  public ProjectNotFoundException(String message) {
    super(message, HttpStatus.NOT_FOUND, null);
  }
}
