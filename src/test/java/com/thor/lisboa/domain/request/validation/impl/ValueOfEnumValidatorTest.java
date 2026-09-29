package com.thor.lisboa.domain.request.validation.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thor.lisboa.domain.request.validation.ValueOfEnum;
import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort.Direction;

class ValueOfEnumValidatorTest {

  @ValueOfEnum(enumClass = Direction.class, excluded = "ASC")
  private String direction;

  @Test
  void acceptsBlankValuesAndNonExcludedEnumConstants() throws Exception {
    var constraint = getClass().getDeclaredField("direction").getAnnotation(ValueOfEnum.class);
    var validator = new ValueOfEnumValidator();
    validator.initialize(constraint);
    ConstraintValidatorContext context = null;

    assertTrue(validator.isValid(null, context));
    assertTrue(validator.isValid(" ", context));
    assertTrue(validator.isValid("DESC", context));
    assertFalse(validator.isValid("ASC", context));
    assertFalse(validator.isValid("INVALID", context));
  }
}
