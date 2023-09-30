package com.elms.auth.validation;

import com.elms.auth.validation.impl.DeviceSubKindValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DeviceSubKindValidation.class)
@Documented
public @interface DeviceSubKind {
    boolean allowNull() default true;
    String message() default "Device sub kind invalid.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
