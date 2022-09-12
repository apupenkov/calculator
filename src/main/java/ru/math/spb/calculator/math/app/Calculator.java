package ru.math.spb.calculator.math.app;

import ru.math.spb.calculator.math.exception.IncorrectExpressionException;
import ru.math.spb.calculator.math.operation.Operation;

import static ru.math.spb.calculator.math.operation.Checker.checkInputText;
import static ru.math.spb.calculator.math.operation.Operation.calculate;
import static ru.math.spb.calculator.math.operation.Operation.getPolishReverseNotationArray;

public final class Calculator {

    private String expression;
    private String result;

//    private static Logger log = LogManager.getLogger(Calculator.class);

    public String calculate() {
        String result = "";
        try {
            checkInputText(expression);
            result = String.valueOf(Operation.calculate(getPolishReverseNotationArray(expression)));
        } catch (IncorrectExpressionException e) {
//                log.error(e);
            System.out.println("Введите корректное выражение:" + e);
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
