package app;

import exception.IncorrectExpressionException;

import java.util.Scanner;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import static operation.Checker.checkInputText;
import static operation.Operation.calculate;
import static operation.Operation.getPolishReverseNotationArray;

public final class Calculator {

    private static Logger log = LogManager.getLogger(Calculator.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String out = "";
        while(!out.equals("q")) {
            try {
                out = scanner.nextLine();
                checkInputText(out);
                double result = calculate(getPolishReverseNotationArray(out));
                System.out.println(result);
            } catch (IncorrectExpressionException e) {
                log.error(e);
                System.out.println("Введите корректное выражение:");
            }
        }
    }
}