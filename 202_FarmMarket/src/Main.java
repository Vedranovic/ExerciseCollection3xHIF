import enums.Category;
import pojos.Product;
import pojos.ProductTools;
import pojos.RandomData;

import java.util.*;

public class Main {
    private static List<Product> products;
    private static Scanner scanner;

    public static void main() {
        products = new ArrayList<>();
        scanner = new Scanner(System.in);
        int choice = 1;

        while (choice != 0) {
            System.out.println("=== FarmMarket ===");
            System.out.println("1) Generate Random Products");
            System.out.println("2) Show All Products");
            System.out.println("3) Filtering");
            System.out.println("4) Sorting");
            System.out.println("0) End");
            System.out.println();
            System.out.print("Selection: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> generate();
                case 2 -> showAll();
                case 3 -> filter();
                case 4 -> sort();
            }
        }
    }

    public static void generate() {
        System.out.print("Number of Products: ");
        int count = scanner.nextInt();

        products = RandomData.randomProducts(count);
        System.out.println("OK - generated!");
        System.out.println();
    }

    public static void showAll() {
        if (products.isEmpty()) {
            System.out.println("No products!");
            System.out.println();
        } else {
            ProductTools.printAll(products);
        }
    }

    public static void filter() {
        if (products.isEmpty()) {
            System.out.println("Please generate some products first!");
            System.out.println();
        } else {
            char choice = 'a';

            while (!(choice >= 'A' && choice <= 'F')) {
                System.out.println("--- FILTER ---");
                System.out.println("A) Organic products");
                System.out.println("B) Price >= X");
                System.out.println("C) Stock <= Y");
                System.out.println("D) Category = ...");
                System.out.println("E) Name contains text (case-insensitive)");
                System.out.println("F) Price between MIN and MAX");
                System.out.print("Selection (A-F): ");
                choice = scanner.next().charAt(0);
                System.out.println();

                switch (choice) {
                    case 'A' -> ProductTools.printAll(ProductTools.filter(products,
                            product -> product.isOrganic()));
                    case 'B' -> {
                        int x = Main.readInt("X: ");
                        ProductTools.printAll(ProductTools.filter(products,
                                product -> product.getPrice() >= x));
                    }
                    case 'C' -> {
                        int y = Main.readInt("Y: ");
                        ProductTools.printAll(
                                ProductTools.filter(products,
                                        product -> product.getStock() <= y)
                        );
                    }
                    case 'D' -> {
                        Category category = Main.readCategory("Category: ");
                        ProductTools.printAll(
                            ProductTools.filter(products,
                                    product -> product.getCategory().equals(category))
                    );
                    }
                    case 'E' -> {
                        String text = Main.readString("Text: ").toLowerCase();
                        ProductTools.printAll(
                            ProductTools.filter(products,
                                    product -> product.getName().toLowerCase().contains(text)
                            )
                    );
                    }
                    case 'F' -> {
                        double min = Main.readDouble("MIN: ");
                        double max = Main.readDouble("MAX: ");
                        ProductTools.printAll(
                            ProductTools.filter(products,
                                    product -> product.getPrice() >= min
                                            && product.getPrice() <= max)
                        );
                    }
                }
            }
        }
    }

    public static void sort() {
        if (products.isEmpty()) {
            System.out.println("Please generate some products first!");
            System.out.println();
        } else {
            char choice = 'a';
            List<Product> sortedList = new ArrayList<>(products);

            while (!(choice >= 'A' && choice <= 'F')) {
                System.out.println("--- SORT ---");
                System.out.println("A) by price ascending");
                System.out.println("B) by price descending");
                System.out.println("C) by stock ascending");
                System.out.println("D) by name (case-insensitive)");
                System.out.println("E) by category, then by name");
                System.out.println("F) bio first, then price ascending");
                System.out.print("Selection (A-F): ");
                choice = scanner.next().charAt(0);
                System.out.println();

                switch (choice) {
                    case 'A' -> sortedList.sort((o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()));
                    case 'B' -> sortedList.sort(((o1, o2) -> Double.compare(o2.getPrice(), o1.getPrice())));
                    case 'C' -> sortedList.sort(((o1, o2) -> o1.getStock() - o2.getStock()));
                    case 'D' -> sortedList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
                    case 'E' -> sortedList.sort((o1, o2) -> {
                        if (o1.getCategory().equals(o2.getCategory())) {
                            return o1.getName().compareTo(o2.getName());
                        }

                        return o1.getCategory().compareTo(o2.getCategory());
                    });
                    case 'F' -> sortedList.sort((o1, o2) -> {
                        int compare = Boolean.compare(o2.isOrganic(), o1.isOrganic());

                        if (compare != 0) {
                            return compare;
                        }

                        return Double.compare(o1.getPrice(), o2.getPrice());
                    });
                }
            }

            ProductTools.printAll(sortedList);
        }
    }

    public static String readString(String prompt) {
        System.out.print(prompt);

        return scanner.next();
    }

    public static int readInt(String prompt) {
        System.out.print(prompt);

        return scanner.nextInt();
    }

    public static double readDouble(String prompt) {
        System.out.print(prompt);

        return scanner.nextDouble();
    }

    public static Category readCategory(String prompt) {
        System.out.println("Categories: " + Arrays.toString(Category.values()));

        while (true) {
            System.out.print(prompt);
            String category = scanner.next().toUpperCase();

            try {
                return Category.valueOf(category);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid.");
            }
        }
    }
}
