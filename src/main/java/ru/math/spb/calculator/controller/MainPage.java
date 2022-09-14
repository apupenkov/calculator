package ru.math.spb.calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.math.spb.calculator.math.app.Calculator;
import ru.math.spb.calculator.model.CalculationEntity;

import javax.validation.Valid;

@Controller
public class MainPage {

    private Calculator calculator;

    public MainPage(Calculator calculator) {
        this.calculator = calculator;
    }

    @GetMapping("/")
    public String getRoot(@ModelAttribute("calculationEntity") CalculationEntity calculationEntity) {
        return "index";
    }

    @GetMapping("/homePage")
    public String getMainPage(@ModelAttribute("calculationEntity") CalculationEntity calculationEntity) {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@ModelAttribute("calculationEntity") @Valid CalculationEntity calculationEntity,
                            BindingResult bindingResult) {
        calculationEntity.setResult(calculator.calculate(calculationEntity.getExpression()));
        return "index";
    }
}
