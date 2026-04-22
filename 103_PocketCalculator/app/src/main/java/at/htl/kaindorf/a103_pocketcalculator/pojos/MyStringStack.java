package at.htl.kaindorf.a103_pocketcalculator.pojos;

public class MyStringStack {
    private String[] data;
    private int top;

    public MyStringStack(int capacity) {
        data = new String[capacity];
        top = -1;
    }

    public boolean isEmpty() {
        return top < 0;
    }

    public String pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        return data[top--];
    }

    public String peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        return data[top];
    }

    public String push(String value) {
        if (top >= data.length - 1) {
            throw new RuntimeException("Stack is full");
        }

        return data[++top] = value;
    }

    public String[] getData() {
        return data;
    }

    public int getTop() {
        return top;
    }
}
