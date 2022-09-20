package ru.math.spb.calculator.math.operation;

import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Separation {

    ArrayDeque<String> separateOnNumberAndOperator(String str) {
        ArrayList<String> result = new ArrayList<>();
        String patternStr = "((\\d*\\.?\\d+)|([+\\-*/()]))";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher m = pattern.matcher(str);
        while(m.find()) {
            result.add(m.group());
        }
        return addMultiplyOperatorIfNeed(result);
    }

    private ArrayDeque<String> addMultiplyOperatorIfNeed(ArrayList<String> arrayDeque) {
        for (int i = 0; i < arrayDeque.size(); i++) {
            if((i-1) >= 0 && (i+1) < arrayDeque.size()) {
                if (arrayDeque.get(i).equals("(")
                        && Pattern.compile("\\d+(\\.\\d+)?").matcher(arrayDeque.get(i-1)).find()) {
                    arrayDeque.add(i, "*");
                }
                else if(arrayDeque.get(i).equals(")")
                        && Pattern.compile("\\d+(\\.\\d+)?").matcher(arrayDeque.get(i+1)).find()) {
                    arrayDeque.add(i+1, "*");
                }
            }
        }
        return new ArrayDeque<>(arrayDeque);
    }
}
