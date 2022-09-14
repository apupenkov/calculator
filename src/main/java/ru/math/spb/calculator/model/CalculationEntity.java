package ru.math.spb.calculator.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/*
* Если указать аннотацию @Component, то при вызове метода, запрашивающего данный объект-модель с ошибкой, будет
* выброшено исключение и аннотации поля expression не сработают.
* */
public class CalculationEntity {

    @Size(min=3, message = "Выражение должно содержать больше 3х символов")
    @Pattern(regexp = "[\\d()/+\\-*.]+", message = "Выражение может содержать только целые числа, " +
            "числа с плавающей точкой и символы +-*/")
    private String expression;
    private String result;

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
