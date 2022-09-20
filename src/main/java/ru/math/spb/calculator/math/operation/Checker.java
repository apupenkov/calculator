package ru.math.spb.calculator.math.operation;

import org.springframework.stereotype.Component;
import ru.math.spb.calculator.math.exception.IncorrectExpressionException;

import java.util.regex.Pattern;

@Component
public class Checker {

    public void checkIsNull(String str) throws NullPointerException{
        if(str == null || "".equals(str) || " ".equals(str) || "\n".equals(str) || "\t".equals(str)) {
            throw new NullPointerException("Вместо выражения передан Null");
        }
    }

    public void checkInputText(String str) throws IncorrectExpressionException {
        if(Pattern.compile("[^\\d()/+\\-*.]+").matcher(str).find()){
            throw new IncorrectExpressionException("Выражение может содержать только целые числа, числа с плавающей" +
                    " точкой, операторы */+-");
        }
        if(Pattern.compile("[/+\\-*.][/+\\-*.]").matcher(str).find()) {
            throw new IncorrectExpressionException("Оператор не может стоять следующим за оператором!");
        }
    }
}
