package ru.math.spb.calculator.controller;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.math.spb.calculator.math.app.Calculator;

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
    public String calculate(@ModelAttribute("calculator") Calculator calculator) {
        calculator.setResult(calculator.calculate());
        return "index";
    }
}
