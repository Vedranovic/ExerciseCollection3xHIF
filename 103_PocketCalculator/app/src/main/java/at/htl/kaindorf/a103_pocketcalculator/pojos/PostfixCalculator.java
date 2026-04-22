package at.htl.kaindorf.a103_pocketcalculator.pojos;

import java.util.Objects;

public class PostfixCalculator {
    public double evalPostfix(MyStringQueue postfix) {
        MyDoubleStack myDoubleStack = new MyDoubleStack(postfix.getData().length);
        int i = 0;

        while (i < postfix.getData().length && postfix.getData()[i] != null) {
            if (ShuntingYardImpl.isNumber(postfix.getData()[i])) {
                myDoubleStack.push(Double.parseDouble(postfix.getData()[i].replace(",", ".")));
            }

            if (isOperator(postfix.getData()[i])) {
                double num1 = myDoubleStack.pop();
                double num2 = myDoubleStack.pop();
                String op = postfix.getData()[i];
                double result = 0.0;

                switch (op) {
                    case "+":
                        result = num2 + num1;
                        break;
                    case "-":
                        result = num2 - num1;
                        break;
                    case "*":
                        result = num2 * num1;
                        break;
                    case "/":
                        result = num2 / num1;
                        break;
                }

                myDoubleStack.push(result);
            }

            i++;
        }

        return myDoubleStack.pop();
    }

    private boolean isOperator(String op) {
        return Objects.equals(op, "+") || Objects.equals(op, "-") ||Objects.equals(op, "*") ||Objects.equals(op, "/");
    }
}
