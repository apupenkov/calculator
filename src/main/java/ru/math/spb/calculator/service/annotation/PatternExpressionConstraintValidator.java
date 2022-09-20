package ru.math.spb.calculator.service.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PatternExpressionConstraintValidator implements ConstraintValidator<PatternSingleOperator, String> {

    private String expression;

    @Override
    public void initialize(PatternSingleOperator patternSingleOperator) {

    }

    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        return !Pattern.compile(".*[+-/*]{2,}.*").matcher(str).find();
    }
}
