package fastfood;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        FoodCabinet foodCabinet = new FoodCabinet();
        Thread cook = new Thread(new Cook(foodCabinet));
        Thread deliverer = new Thread(new Deliverer(foodCabinet));

        cook.start();
        deliverer.start();

        cook.join();
        deliverer.join();
    }
}
