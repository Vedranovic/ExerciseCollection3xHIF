package fastfood;

import java.util.Random;

public class Deliverer implements Runnable {
    private final FoodCabinet foodCabinet;
    private final Random random = new Random();

    public Deliverer(FoodCabinet foodCabinet) {
        this.foodCabinet = foodCabinet;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(random.nextInt(1, 1001));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            String menu = foodCabinet.removeMenu();

            System.out.println(menu + " delivered");
        }
    }
}
