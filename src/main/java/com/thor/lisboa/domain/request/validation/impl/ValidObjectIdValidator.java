package com.thor.lisboa.domain.request.validation.impl;

import com.thor.lisboa.domain.request.validation.ValidObjectId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;

public class ValidObjectIdValidator implements ConstraintValidator<ValidObjectId, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    return StringUtils.isBlank(value) || ObjectId.isValid(value);
  }
}
