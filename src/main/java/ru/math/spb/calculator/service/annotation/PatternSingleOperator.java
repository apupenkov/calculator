package ru.math.spb.calculator.service.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=PatternExpressionConstraintValidator.class)
public @interface PatternSingleOperator {
    String message() default "PatternExpression";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
