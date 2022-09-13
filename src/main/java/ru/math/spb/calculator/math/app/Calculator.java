package ru.math.spb.calculator.math.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.math.spb.calculator.CalculatorApplication;
import ru.math.spb.calculator.math.exception.IncorrectExpressionException;
import ru.math.spb.calculator.math.operation.Operation;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.Arrays;

import static ru.math.spb.calculator.math.operation.Checker.checkInputText;
import static ru.math.spb.calculator.math.operation.Operation.getPolishReverseNotationArray;

public final class Calculator {

    @Size(min=3, message = "Выражение должно содержать больше 3х символов")
    @Pattern(regexp = "[\\d()/+\\-*.]+", message = "Выражение может содержать только целые числа, " +
            "числа с плавающей точкой и символы +-*/")
    private String expression;
    private String result;

    private static final Logger log = LoggerFactory.getLogger(CalculatorApplication.class);

    public String calculate() {
        String result = "";
        try {
            checkInputText(expression);
            result = String.valueOf(Operation.calculate(getPolishReverseNotationArray(expression)));
        } catch (IncorrectExpressionException e) {
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
        }
        return result;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
