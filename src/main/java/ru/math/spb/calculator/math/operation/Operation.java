package ru.math.spb.calculator.math.operation;

import org.springframework.stereotype.Component;
import ru.math.spb.calculator.math.exception.IncorrectExpressionException;
import ru.math.spb.calculator.math.operator.Operator;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.regex.Pattern;

@Component
public class Operation {

    private Separation separation;
    private Operator operator;

    public Operation(Separation separation, Operator operator) {
        this.separation = separation;
        this.operator = operator;
    }

    private Double operate(String a, String b, String operator) {
        Double firstNum = Double.parseDouble(a);
        Double secondNum = Double.parseDouble(b);
        switch (operator) {
            case "*": return firstNum * secondNum;
            case "/": return  firstNum / secondNum;
            case "+": return  firstNum + secondNum;
            case "-": return  firstNum - secondNum;
        }
        return -1.0;
    }

    public ArrayDeque<String> getPolishReverseNotationArray(String str) throws IncorrectExpressionException {

        ArrayDeque<String> arrayDequeSymbols = separation.separateOnNumberAndOperator(str);

        if(Pattern.compile("[^\\d(]+").matcher(arrayDequeSymbols.getFirst()).find()
            || Pattern.compile("[^\\d)]+").matcher(arrayDequeSymbols.getLast()).find()) {
            throw new IncorrectExpressionException("Выражение должно начинаться с числа или символа ( и " +
                    "заканчиваться числом или символом )");
        }

        ArrayDeque<String> reversNotation = new ArrayDeque<>();
        ArrayDeque<String> stackOperators = new ArrayDeque<>();

        for (String el: arrayDequeSymbols) {
            if (!operator.isOperator(el)) {
                reversNotation.add(el);
            } else {
                if (!stackOperators.isEmpty()) {
                    if (el.equals(")")) {
                        String[] tempArr = stackOperators.toArray(String[]::new);
                        for (int i = tempArr.length - 1; i > 0; i--) {
                            if(!tempArr[i].equals("(")) {
                                reversNotation.add(stackOperators.removeLast());
                            } else {
                                stackOperators.removeLast();
                                break;
                            }
                        }
                    } else {
                        if (operator.getPriority(el) > operator.getPriority(stackOperators.getLast()) || el.equals("(")) {
                            stackOperators.add(el);
                        } else {
                            reversNotation.add(stackOperators.removeLast());
                            stackOperators.add(el);
                        }
                    }
                } else {
                    stackOperators.add(el);
                }
            }
        }
        stackOperators.stream().forEach(el -> {
            if(!el.equals("(") && !el.equals(")")) {
                reversNotation.add(Objects.requireNonNull(stackOperators.pollLast()));
            }
        });
        for (String el : reversNotation) {
            if (el.equals("(") || el.equals(")")) {
                throw new IncorrectExpressionException("Некорректно составленное выражение");
            }
        }
        return reversNotation;
    }

    public double calculate(ArrayDeque<String> polishReverseNotationArray) {

        LinkedList<String> tempArray = new LinkedList<>(polishReverseNotationArray);
        double tempResult = 0.0;
        while(tempArray.size() > 1) {
            for (String el: tempArray){
                int indexOfFirstNum;
                int indexOfSecondNum;

                if(tempResult == 0.0) {
                    if(operator.isOperator(el)) {
                        indexOfFirstNum = tempArray.indexOf(el) - 2;
                        indexOfSecondNum = tempArray.indexOf(el) - 1;
                        tempResult = operate(
                                tempArray.get(indexOfFirstNum),
                                tempArray.get(indexOfSecondNum),
                                el
                        );
                        tempArray.remove(indexOfSecondNum);
                        tempArray.remove(indexOfSecondNum - 1);
                        tempArray.remove(indexOfFirstNum);
                        tempArray.add(indexOfFirstNum, String.valueOf(tempResult));
                        break;
                    }
                } else {
                    if(operator.isOperator(el)) {
                        indexOfFirstNum = tempArray.indexOf(el) - 2;
                        indexOfSecondNum = tempArray.indexOf(el) - 1;
                        tempResult = operate(
                                tempArray.get(indexOfFirstNum),
                                tempArray.get(indexOfSecondNum),
                                el
                        );
                        tempArray.remove(indexOfSecondNum);
                        tempArray.remove(indexOfSecondNum);
                        tempArray.remove(indexOfSecondNum - 1);
                        tempArray.add(indexOfSecondNum - 1, String.valueOf(tempResult));
                        break;
                    }
                }
            }
        }
        return tempResult;
    }
}
