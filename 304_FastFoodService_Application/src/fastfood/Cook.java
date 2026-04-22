package fastfood;

import java.util.Locale;
import java.util.Random;

public class Cook implements Runnable {
    private final FoodCabinet foodCabinet;
    private final Random random = new Random();

    public Cook(FoodCabinet foodCabinet) {
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

            foodCabinet.addMenu(String.format(Locale.GERMAN, "M%02d", i + 1));

            System.out.println(String.format(Locale.GERMAN, "M%02d ready", i + 1));
        }
    }
}
