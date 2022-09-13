package ru.math.spb.calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.math.spb.calculator.math.app.Calculator;

import javax.validation.Valid;

@Controller
public class MainPage {

    @GetMapping("/")
    public String getRoot(@ModelAttribute("calculator") Calculator calculator) {
        return "index";
    }

    @GetMapping("/homePage")
    public String getMainPage(@ModelAttribute("calculator") Calculator calculator) {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@ModelAttribute("calculator") @Valid Calculator calculator, BindingResult bindingResult) {
        calculator.setResult(calculator.calculate());
        return "index";
    }
}
