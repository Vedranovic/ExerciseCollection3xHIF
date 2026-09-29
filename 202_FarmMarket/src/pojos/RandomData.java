package pojos;

import enums.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomData {
    private static final String[] NAMES = {"Milk", "Cheese", "Yogurt", "Apples",
            "Potatoes", "Carrots", "Steak", "Sausage", "Juice", "Water"};

    public static List<Product> randomProducts(int count) {
        Random random = new Random();
        List<Product> randomProducts = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            char letter = (char) ('A' + random.nextInt(6));

            randomProducts.add(new Product(
                    Category.values()[random.nextInt(Category.values().length)],
                    i + 1,
                    NAMES[random.nextInt(NAMES.length)] + "-" + letter,
                    random.nextDouble(0.50, 20.51),
                    random.nextInt(120),
                    random.nextBoolean()
            ));
        }

        return randomProducts;
    }
}
