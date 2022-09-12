package ru.math.spb.calculator.math.operation;

import ru.math.spb.calculator.math.exception.IncorrectExpressionException;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.stream.Collectors;

import static ru.math.spb.calculator.math.operation.Checker.checkIncorrectString;
import static ru.math.spb.calculator.math.operator.Operator.isOperator;

final class Separation {

    static ArrayDeque<String> separateOnNumberAndOperator(String str) throws IncorrectExpressionException {
        String[] items = separateAllSymbolsAndRemoveSpaces(str);
        checkIncorrectString(items);
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
                        && (result.isEmpty() || !isOperator(result.getLast()))
                        || ((!result.isEmpty() && result.getLast().matches("\\d+\\."))
                        && items[i].matches("\\d+"))) {
                    result.addLast(result.removeLast() + items[i]);
                } else {
                    if(items[i-1].matches("\\d+(\\.\\d+)?") && items[i].equals("(")) {
                        result.addLast("*");
                        result.addLast(items[i]);
                    }
                    else if(items[i].equals(")") && items[i+1].matches("\\d+(\\.\\d+)?")) {
                        result.addLast(items[i]);
                        result.addLast("*");
                    } else {
                        result.addLast(items[i]);
                    }
                }
            }
        }
        return result;
    }

    private static String[] separateAllSymbolsAndRemoveSpaces(String str) {
        return Arrays.stream(str.split(""))
                .filter(el -> !el.matches("\\s+")).collect(Collectors.toList()).toArray(String[]::new);
    }
}
