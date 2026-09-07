package com.thor.lisboa.domain.exception;

import org.springframework.http.HttpStatus;

public class ProjectIntegrationException extends ProjectException{

  public ProjectIntegrationException(String message, HttpStatus status) {
    super(message, status, null);
  }
}
