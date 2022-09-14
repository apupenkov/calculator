package ru.math.spb.calculator.math.operation;

import org.springframework.stereotype.Component;
import ru.math.spb.calculator.math.exception.IncorrectExpressionException;
import ru.math.spb.calculator.math.operator.Operator;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class Separation {

    private Checker checker;
    private Operator operator;

    public Separation(Checker checker, Operator operator) {
        this.checker = checker;
        this.operator = operator;
    }

    ArrayDeque<String> separateOnNumberAndOperator(String str) throws IncorrectExpressionException {
        String[] items = separateAllSymbolsAndRemoveSpaces(str);
        checker.checkIncorrectString(items);
        ArrayDeque<String> result = new ArrayDeque<>();
        for (int i = 0; i < items.length; i++) {
            if(!items[i].equals(" ")) {
                if ((items[i].matches("\\d+") && !result.isEmpty()) && result.getLast().matches("\\d+(\\.)?")) {
                    result.addLast(result.removeLast() + items[i]);
                } else if (items[i].matches("\\d+")
                        && (result.isEmpty() || !result.getLast().matches("\\d+\\."))
                        && !items[i].equals(".")) {
                    result.addLast(items[i]);
                } else if (items[i].equals(".")
                        && (result.isEmpty() || !operator.isOperator(result.getLast()))
                        || ((!result.isEmpty() && result.getLast().matches("\\d+\\."))
                        && items[i].matches("\\d+"))) {
                    result.addLast(result.removeLast() + items[i]);
                } else {
                    if((i-1) >= 0 && (i+1) < items.length) {
                        if (items[i - 1].matches("\\d+(\\.\\d+)?") && items[i].equals("(")) {
                            result.addLast("*");
                            result.addLast(items[i]);
                        } else if (items[i].equals(")") && items[i + 1].matches("\\d+(\\.\\d+)?")) {
                            result.addLast(items[i]);
                            result.addLast("*");
                        } else {
                            result.addLast(items[i]);
                        }
                    }
                }
            }
        }
        return result;
    }

    private String[] separateAllSymbolsAndRemoveSpaces(String str) {
        return Arrays.stream(str.split(""))
                .filter(el -> !el.matches("\\s+")).collect(Collectors.toList()).toArray(String[]::new);
    }
}
