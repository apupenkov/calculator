package ru.math.spb.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.math.spb.calculator.math.app.Calculator;
import ru.math.spb.calculator.math.exception.IncorrectExpressionException;

import java.util.stream.Stream;

@SpringBootTest
class CalculatorApplicationTests {

    @Autowired
    private Calculator calculator;

    private static Stream<String> blankStrings() {
        return Stream.of("","\t"," ","\n", null);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/failResources/failTestData.csv", encoding = "utf-8", delimiter = ';', numLinesToSkip = 1)
    void incorrectExpression(String expression) {
        Assertions.assertThrows(IncorrectExpressionException.class, () -> calculator.calculate(expression));
    }

    @ParameterizedTest
    @MethodSource("blankStrings")
    void emptyExpression(String expression) {
        Assertions.assertThrows(NullPointerException.class, () -> calculator.calculate(expression));
    }
}
