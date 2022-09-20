package ru.math.spb.calculator.math.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.math.spb.calculator.math.exception.IncorrectExpressionException;
import ru.math.spb.calculator.math.operation.Checker;
import ru.math.spb.calculator.math.operation.Operation;
import ru.math.spb.calculator.model.ExpressionEntity;

import java.util.Arrays;

@Slf4j
@Component
public class Calculator {

    private Checker checker;
    private Operation operation;

    public Calculator(Checker checker, Operation operation) {
        this.checker = checker;
        this.operation = operation;
    }

    public String calculate(String expression) throws IncorrectExpressionException, NullPointerException {
        String result = "";
        checker.checkIsNull(expression);
        checker.checkInputText(expression);
        result = String.valueOf(operation.calculate(operation.getPolishReverseNotationArray(expression)));
        return result;
    }

    public String calculateAndCheckResult(String expression) {
        String result = "";
        try {
            result = calculate(expression);
        } catch (NullPointerException | IncorrectExpressionException e) {
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
        }
        return result;
    }
}
