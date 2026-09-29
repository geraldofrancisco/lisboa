package com.thor.lisboa.adapters.out.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.thor.lisboa.domain.exception.ProjectBusinessException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import java.util.Locale;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

class ProjectExceptionHandlerTest {

  private final MessageSource messageSource = mock(MessageSource.class);
  private final ProjectExceptionHandler handler = new ProjectExceptionHandler(messageSource);

  ProjectExceptionHandlerTest() {
    when(messageSource.getMessage(anyString(), isNull(), any(Locale.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));
  }

  @Test
  void mapsUnexpectedExceptionsToInternalServerError() {
    var response = handler.handlerException(new IllegalStateException("unexpected"));

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    assertEquals("PROJECT_GENERIC_EXCEPTION",
        response.getBody().getErrorDescription());
  }

  @Test
  void mapsProjectExceptionsUsingTheirStatusAndMessage() {
    var response = handler.handlerProjectException(new ProjectBusinessException("business.error"));

    assertEquals(HttpStatus.UNPROCESSABLE_CONTENT, response.getStatusCode());
    assertEquals("business.error", response.getBody().getErrorDescription());
  }

  @Test
  void mapsConstraintViolationsToFieldErrors() {
    @SuppressWarnings("unchecked")
    ConstraintViolation<Object> violation = mock(ConstraintViolation.class);
    Path path = mock(Path.class);
    when(violation.getPropertyPath()).thenReturn(path);
    when(path.toString()).thenReturn("request.email.address");
    when(violation.getMessage()).thenReturn("invalid.email");

    var response = handler.handlerConstraintViolationException(
        new ConstraintViolationException(Set.of(violation)));

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("address", response.getBody().getFields().getFirst().getName());
    assertEquals("invalid.email", response.getBody().getFields().getFirst().getMessage());
  }

  @Test
  void mapsBindingErrorsToFieldErrors() {
    var bindingResult = new BeanPropertyBindingResult(new Object(), "request");
    bindingResult.addError(new FieldError("request", "title", "invalid.title"));
    var exception = new MethodArgumentNotValidException(null, bindingResult);

    var response = handler.handlerMethodArgumentNotValidException(exception);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("title", response.getBody().getFields().getFirst().getName());
    assertEquals("invalid.title", response.getBody().getFields().getFirst().getMessage());
  }
}
