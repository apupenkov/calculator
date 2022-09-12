package ru.math.spb.calculator.math.operation;

import ru.math.spb.calculator.math.exception.IncorrectExpressionException;

import java.util.regex.Pattern;

import static ru.math.spb.calculator.math.operator.Operator.isOperator;

public final class Checker {

    static void checkIncorrectString(String[] items) throws IncorrectExpressionException {
        if(items.length > 2) {
            for (int i = 0; i < items.length; i++) {
                if (i == 0 || i == items.length - 2) {
                    if ((isOperator(items[i]) && !items[i].equals("("))
                            && (isOperator(items[i + 1]) && !items[i + 1].equals(")"))) {
                        throw new IncorrectExpressionException("Оператор не может стоять в начале или конце строки," +
                                " также как и закрывающая и открывающая скобки соответственно!");
                    }
                } else {
                    if (i + 1 != items.length - 1) {
                        if ((isOperator(items[i]) && !items[i].equals(")"))
                                && (isOperator(items[i + 1]) && !items[i + 1].equals("("))) {
                            throw new IncorrectExpressionException("Оператор не может стоять следующим за оператором!");
                        }
                    }
                }
            }
        } else {
            throw new IncorrectExpressionException("Выражение должно содержать больше 2-х символов!");
        }
    }

    public static void checkInputText(String str) throws IncorrectExpressionException {
        if(Pattern.compile("[^\\d()/+\\-*.]+").matcher(str).find()){
            throw new IncorrectExpressionException("Выражение может содержать только целые числа, числа с плавающей" +
                    " точкой, операторы */+-");
        }
    }
}
