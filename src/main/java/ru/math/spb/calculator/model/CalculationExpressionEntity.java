package ru.math.spb.calculator.model;

import org.springframework.validation.annotation.Validated;
import ru.math.spb.calculator.service.annotation.PatternSingleOperator;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/*
* Если указать аннотацию @Component, то при вызове метода, запрашивающего данный объект-модель с ошибкой, будет
* выброшено исключение и аннотации поля expression не сработают.
* */
@Validated
public class CalculationExpressionEntity implements ExpressionEntity {

    @Size(min=3, message = "Выражение должно содержать больше 3х символов")
    @Pattern(regexp = "[\\d()/+\\-*.]+", message = "Выражение может содержать только целые числа, " +
            "числа с плавающей точкой и символы +-*/")
    @Pattern(regexp = "^[\\d(].+",
            message = "Выражение должно начинаться с числа или символа (")
    @Pattern(regexp = ".+(\\d+(\\.?)\\d+|\\))$",
            message = "Выражение должно заканчиваться числом или символом )")
    @PatternSingleOperator(message = "Операторы в выражении не должны следовать друг за другом")
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
