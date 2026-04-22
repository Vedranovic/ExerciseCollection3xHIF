package at.htl.kaindorf.a103_pocketcalculator.pojos;

import java.util.ArrayList;
import java.util.List;

public class ShuntingYardImpl {
    public static MyStringQueue toPostfix(String infix) {
        MyStringQueue myStringQueue = new MyStringQueue(infix.length());
        MyStringStack myStringStack = new MyStringStack(infix.length());
        List<String> tokens = ShuntingYardImpl.tokenize(infix);

        for (int i = 0; i < tokens.size(); i++) {
            if (isNumber(tokens.get(i))) {
                myStringQueue.enqueue(tokens.get(i));
            }

            if (isOperator(tokens.get(i)) || tokens.get(i).equals("(") || tokens.get(i).equals(")") ) {
                int precedence = prec(tokens.get(i));

                if (precedence == 2) {
                    try {
                        while (myStringStack.getData()[myStringStack.getTop()].equals("*") || myStringStack.getData()[myStringStack.getTop()].equals("/")) {
                            myStringQueue.enqueue(myStringStack.getData()[myStringStack.getTop()]);
                            myStringStack.pop();
                        }

                        myStringStack.push(tokens.get(i));
                    } catch (ArrayIndexOutOfBoundsException aioobe) {
                        myStringStack.push(tokens.get(i));
                    }
                }

                if (precedence == 1) {
                    try {
                        while (isOperator(myStringStack.getData()[myStringStack.getTop()])) {
                            myStringQueue.enqueue(myStringStack.getData()[myStringStack.getTop()]);
                            myStringStack.pop();
                        }

                        myStringStack.push(tokens.get(i));
                    } catch (ArrayIndexOutOfBoundsException aioobe) {
                        myStringStack.push(tokens.get(i));
                    }
                }

                if (precedence == 0) {
                    myStringStack.push(tokens.get(i));
                }

                if (precedence == -1) {
                    while (!myStringStack.getData()[myStringStack.getTop()].equals("(")) {
                        myStringQueue.enqueue(myStringStack.getData()[myStringStack.getTop()]);
                        myStringStack.pop();
                    }

                    myStringStack.pop();
                }
            }
        }

        while (!myStringStack.isEmpty()) {
            myStringQueue.enqueue(myStringStack.getData()[myStringStack.getTop()]);
            myStringStack.pop();
        }

        return myStringQueue;
    }

    private static List<String> tokenize(String expr) {
        StringBuilder tempStr = new StringBuilder();
        List<String> tokens = new ArrayList<>();

        for (int i = 0; i < expr.length();) {
            if (isTokenNumber(String.valueOf(expr.charAt(i)))) {
                while (i < expr.length() && !isOperator(String.valueOf(expr.charAt(i))) && !(expr.charAt(i) == ')')) {
                    tempStr.append(expr.charAt(i));
                    i++;
                }
            } else {
                tempStr.append(expr.charAt(i));
                i++;
            }

            tokens.add(String.valueOf(tempStr));
            tempStr.setLength(0);
        }

        return tokens;
    }

    public static boolean isTokenNumber(String value) {
        try {
            return Integer.parseInt(value) >= 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean isNumber(String value) {
        try {
            if (value.contains(",")) {
                return Double.parseDouble(value.replace(",", ".")) >= 0;
            } else {
                return Integer.parseInt(value) >= 0;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private static boolean isOperator(String value) {
        return value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/");
    }

    private static int prec(String op) {
        switch (op) {
            case "*":
            case "/":
                return 2;
            case "+":
            case "-":
                return 1;
            case "(":
                return 0;
        }

        return -1;
    }
}
