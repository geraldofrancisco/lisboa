package com.thor.lisboa.domain.request.validation.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;

class ValidObjectIdValidatorTest {

  @Test
  void acceptsBlankValuesAndValidObjectIds() {
    var validator = new ValidObjectIdValidator();
    ConstraintValidatorContext context = null;

    assertTrue(validator.isValid(null, context));
    assertTrue(validator.isValid("", context));
    assertTrue(validator.isValid(" ", context));
    assertTrue(validator.isValid("507f1f77bcf86cd799439011", context));
    assertFalse(validator.isValid("invalid-id", context));
    assertFalse(validator.isValid("123", context));
  }
}
