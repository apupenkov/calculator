package operator;

public final class Operator {

    public static int getPriority(String operator) {
        switch (operator) {
            case "*": return 2;
            case "/": return 2;
            case "+": return 1;
            case "-": return 1;
        }
        return -1;
    }

    public static boolean isOperator(String item){
        return item.equals("(") || item.equals(")") || item.equals("*")
                || item.equals("/") || item.equals("+") || item.equals("-");
    }
}
