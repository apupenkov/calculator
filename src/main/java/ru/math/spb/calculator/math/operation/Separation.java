package ru.math.spb.calculator.math.operation;

import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Separation {

    ArrayDeque<String> separateOnNumberAndOperator(String str) {
        String tempStr = addMultiplyOperatorIfNeed(str);
        ArrayDeque<String> result = new ArrayDeque<>();
        String patternStr = "((\\d*\\.?\\d+)|([+\\-*/()]))";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher m = pattern.matcher(tempStr);
        while(m.find()) {
            result.add(m.group());
        }
        return new ArrayDeque<>(result);
    }

    private String addMultiplyOperatorIfNeed(String str) {
        return str.replaceAll("([^(+-/*])\\(", "$1*(")
                .replaceAll("\\)([^+-/*)])", ")*$1");
    }


    public static void main(String[] args) {
        Separation separation = new Separation();
        System.out.println(separation.separateOnNumberAndOperator("(45.6+(78.99-89.76)((67-98)*78))(78-89)"));
    }
}
