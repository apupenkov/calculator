package ru.math.spb.calculator.math.operator;

import org.springframework.stereotype.Component;

@Component
public class Operator {

    public int getPriority(String operator) {
        switch (operator) {
            case "*": return 2;
            case "/": return 2;
            case "+": return 1;
            case "-": return 1;
        }
        return -1;
    }

    public boolean isOperator(String item){
        return item.equals("(") || item.equals(")") || item.equals("*")
                || item.equals("/") || item.equals("+") || item.equals("-");
    }
}
