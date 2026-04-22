package fastfood;

import java.util.ArrayList;
import java.util.List;

public class FoodCabinet {
    private final List<String> foodCabinet = new ArrayList<>();
    private static final int CAPACITY = 3;

    public synchronized void addMenu(String menu) {
        while (foodCabinet.size() == CAPACITY) {
            try {
                System.out.println("food cabinet is full");
                System.out.println("cook waits");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        foodCabinet.add(menu);
        printListContents();

        notifyAll();
    }

    public synchronized String removeMenu() {
        while (foodCabinet.isEmpty()) {
            try {
                System.out.println("food cabinet is empty");
                System.out.println("deliverer waits");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        String menu = foodCabinet.removeFirst();
        printListContents();

        notifyAll();
        return menu;
    }

    private void printListContents() {
        System.out.format("[%s]\n",
                String.join(", ", foodCabinet));
    }
}
