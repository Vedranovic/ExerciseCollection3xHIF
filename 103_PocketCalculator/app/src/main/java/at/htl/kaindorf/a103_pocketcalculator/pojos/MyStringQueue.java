package at.htl.kaindorf.a103_pocketcalculator.pojos;

public class MyStringQueue {
    private String[] data;
    private int tail;

    public MyStringQueue(int capacity) {
        data = new String[capacity];
        tail = -1;
    }

    public void enqueue(String value) {
        if (tail == data.length - 1) {
            throw new RuntimeException("Queue is full!");
        }

        data[++tail] = value;
    }

    public String dequeue() {
        if (tail < 0) {
            throw new RuntimeException("Queue is full!");
        }

        String value = data[0];

        for (int i = 0; i < tail - 1; i++) {
            data[i] = data[i + 1];
        }

        tail--;

        return value;
    }

    public String peek() {
        if (tail < 0) {
            throw new RuntimeException("Queue is full!");
        }

        return data[0];
    }

    public String[] getData() {
        return data;
    }
}
