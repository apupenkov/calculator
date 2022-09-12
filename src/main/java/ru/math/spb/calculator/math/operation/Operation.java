package ru.math.spb.calculator.math.operation;

import ru.math.spb.calculator.math.exception.IncorrectExpressionException;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Objects;

import static ru.math.spb.calculator.math.operation.Separation.separateOnNumberAndOperator;
import static ru.math.spb.calculator.math.operator.Operator.getPriority;
import static ru.math.spb.calculator.math.operator.Operator.isOperator;

public final class Operation {

    private static Double operate(String a, String b, String operator) {
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

    public static ArrayDeque<String> getPolishReverseNotationArray(String str) throws IncorrectExpressionException {

        ArrayDeque<String> arrayDequeSymbols = separateOnNumberAndOperator(str);
        ArrayDeque<String> reversNotation = new ArrayDeque<>();
        ArrayDeque<String> stackOperators = new ArrayDeque<>();

        for (String el: arrayDequeSymbols) {
            if (!isOperator(el)) {
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
                        if (getPriority(el) > getPriority(stackOperators.getLast()) || el.equals("(")) {
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
        stackOperators.stream().forEach(el -> reversNotation.add(Objects.requireNonNull(stackOperators.pollLast())));
        return reversNotation;
    }

    public static double calculate(ArrayDeque<String> polishReverseNotationArray) {

        LinkedList<String> tempArray = new LinkedList<>(polishReverseNotationArray);
        double tempResult = 0.0;
        while(tempArray.size() > 1) {
            for (String el: tempArray){
                int indexOfFirstNum;
                int indexOfSecondNum;

                if(tempResult == 0.0) {
                    if(isOperator(el)) {
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
                    if(isOperator(el)) {
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
