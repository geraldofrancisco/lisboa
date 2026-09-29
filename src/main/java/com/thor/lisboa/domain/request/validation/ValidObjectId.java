package com.thor.lisboa.domain.request.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.thor.lisboa.domain.request.validation.impl.ValidObjectIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = ValidObjectIdValidator.class)
@Retention(RUNTIME)
@Target({PARAMETER, FIELD})
@ReportAsSingleViolation
public @interface ValidObjectId {

  String message() default "Value is not a valid ObjectId";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
