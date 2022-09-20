package ru.math.spb.calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.math.spb.calculator.math.app.Calculator;
import ru.math.spb.calculator.math.exception.IncorrectExpressionException;
import ru.math.spb.calculator.model.CalculationExpressionEntity;

import javax.validation.Valid;

@Controller
public class MainPage {

    private Calculator calculator;

    public MainPage(Calculator calculator) {
        this.calculator = calculator;
    }

    @GetMapping(value = {"/", "/homePage"})
    public String getRoot(@ModelAttribute("calculationEntity") CalculationExpressionEntity calculationEntity) {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@ModelAttribute("calculationEntity") @Valid CalculationExpressionEntity calculationEntity,
                            BindingResult bindingResult) throws IncorrectExpressionException {
        calculationEntity.setResult(calculator.calculateAndCheckResult(calculationEntity.getExpression()));
        return "index";
    }
}
