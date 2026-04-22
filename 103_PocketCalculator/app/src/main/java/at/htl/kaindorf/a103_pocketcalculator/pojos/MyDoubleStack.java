package at.htl.kaindorf.a103_pocketcalculator.pojos;

public class MyDoubleStack {
    private double[] data;
    private int top;

    public MyDoubleStack(int capacity) {
        data = new double[capacity];
        top = -1;
    }

    public boolean isEmpty() {
        return top < 0;
    }

    public double pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        return data[top--];
    }

    public double peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        return data[top];
    }

    public double push(double value) {
        if (top >= data.length - 1) {
            throw new RuntimeException("Stack is full");
        }

        return data[++top] = value;
    }
}
