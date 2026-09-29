package pojos;

import Enum.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomData {
    private static final String[] NAMES = {
            "Milk", "Cheese", "Yogurt", "Apples",
            "Potatoes", "Carrots", "Steak", "Sausage", "Juice", "Water"
    };

    public static List<Product> randomProducts(int count) {
        List<Product> products = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            products.add(new Product(
                    Category.values()[random.nextInt(6)],
                    i + 1,
                    NAMES[random.nextInt(10)],
                    random.nextDouble(0.50, 20.50),
                    random.nextInt(120),
                    random.nextBoolean()));
        }

        return products;
    }
}
