import pojos.Product;

import Enum.Category;
import pojos.ProductTools;
import pojos.RandomData;

import java.util.*;

public class Main {
    private static List<Product> products;
    private static Scanner scanner;

    public static void main(String[] args) {
        products = new ArrayList<>();
        scanner = new Scanner(System.in);
        int choice = 1;

        while (choice != 0) {
            System.out.println("""
                    === FarmMarket ===
                    1) Generate Random Products
                    2) Show All Products
                    3) Filtering
                    4) Sorting
                    0) End
                    """);

            System.out.print("Selection: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Main.generate();
                    break;
                case 2:
                    Main.showAll();
                    break;
                case 3:
                    Main.filter();
                    break;
                case 4:
                    Main.sort();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Enter a valid number!");
                    break;
            }
        }
    }

    public static void generate() {
        System.out.print("Number of Products: ");
        int count = scanner.nextInt();

        products = RandomData.randomProducts(count);
        System.out.println("OK- generated!");
        System.out.println();
    }

    public static void showAll() {
        if (products.isEmpty()) {
            System.out.println("No products!");
            System.out.println();
            return;
        }

        System.out.println("Result (" + products.size() + "):");
        ProductTools.printAll(products);
    }

    public static void filter() {
        boolean toFilter = true;

        while (toFilter) {
            if (!products.isEmpty()) {
                System.out.println("""
                    === FILTER ===
                    A) Organic Products
                    B) Price >= X
                    C) Stock <= Y
                    D) Category = ...
                    E) Name contains text (case-insensitive)
                    F) Price between MIN and MAX
                    """);

                System.out.print("Selection (A-F): ");
                char choice = scanner.next().charAt(0);

                switch (choice) {
                    case 'A':
                        List<Product> organicList = ProductTools.filter(products, Product::isOrganic);
                        System.out.println("Result (" + organicList.size() + "):");
                        ProductTools.printAll(organicList);
                        toFilter = false;
                        break;
                    case 'B':
                        int x = Main.readInt("X: ");
                        List<Product> xList = ProductTools.filter(products, product -> product.getPrice() >= x);
                        System.out.println("Result (" + xList.size() + "):");
                        ProductTools.printAll(xList);
                        toFilter = false;
                        break;
                    case 'C':
                        int y = Main.readInt("Y: ");
                        List<Product> yList = ProductTools.filter(products, product -> product.getStock() <= y);
                        System.out.println("Result (" + yList.size() + "):");
                        ProductTools.printAll(yList);
                        toFilter = false;
                        break;
                    case 'D':
                        Category category = readCategory();
                        List<Product> filteredCategoryList = ProductTools.filter(products, product -> product.getCategory().equals(category));
                        System.out.println("Result (" + filteredCategoryList.size() + "):");
                        ProductTools.printAll(filteredCategoryList);
                        toFilter = false;
                        break;
                    case 'E':
                        String text = readString("Text: ");
                        List<Product> containsTextList = ProductTools.filter(products, product -> product.getName().toLowerCase().contains(text.toLowerCase()));
                        System.out.println("Result (" + containsTextList.size() + "):");
                        ProductTools.printAll(containsTextList);
                        toFilter = false;
                        break;
                    case 'F':
                        double min = readDouble("MIN: ");
                        double max = readDouble("MAX: ");
                        List<Product> minMaxList = ProductTools.filter(products, product -> product.getPrice() > min && product.getPrice() < max);
                        System.out.println("Result (" + minMaxList.size() + "):");
                        ProductTools.printAll(minMaxList);
                        toFilter = false;
                        break;
                    default:
                        toFilter = false;
                        break;
                }
            } else {
                System.out.println("Please generate some products first!");
                System.out.println();
                toFilter = false;
            }
        }
    }

    public static void sort() {
        boolean toSort = true;

        while (toSort) {
            if (!products.isEmpty()) {
                System.out.println("""
                    === SORT ===
                    A) by price ascending
                    B) by price descending
                    C) by stock descending
                    D) by name (case.insensitive)
                    E) by category, then by name
                    F) bio first, then price ascending
                    """);

                System.out.print("Selection (A-F): ");
                char choice = scanner.next().charAt(0);
                System.out.println("Result:");

                List<Product> sortedList = new ArrayList<>(products);

                switch (choice) {
                    case 'A':
                        sortedList.sort((o1, o2) -> (int) (o1.getPrice() - o2.getPrice()));
                        ProductTools.printAll(sortedList);
                        toSort = false;
                        break;
                    case 'B':
                        sortedList.sort((o1, o2) -> (int) (o2.getPrice() - o1.getPrice()));
                        ProductTools.printAll(sortedList);
                        toSort = false;
                        break;
                    case 'C':
                        sortedList.sort((o1, o2) -> (o2.getStock() - o1.getStock()));
                        ProductTools.printAll(sortedList);
                        toSort = false;
                        break;
                    case 'D':
                        sortedList.sort(Comparator.comparing(Product::getName));
                        ProductTools.printAll(sortedList);
                        toSort = false;
                        break;
                    case 'E':
                        sortedList.sort(Comparator.comparing((Product o) -> o.getCategory().name()).thenComparing(Product::getName));
                        ProductTools.printAll(sortedList);
                        toSort = false;
                        break;
                    case 'F':
                        sortedList.sort(Comparator.comparing(Product::isOrganic).reversed().thenComparing(Product::getPrice));
                        ProductTools.printAll(sortedList);
                        toSort = false;
                        break;
                    default:
                        toSort = false;
                        break;
                }
            } else {
                System.out.println("Please generate some products first!");
                System.out.println();
                toSort = false;
            }
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

    public static Category readCategory() {
        String input;

        while (true) {
            try {
                System.out.println("Categories: " + Arrays.toString(Category.values()));
                System.out.print("Category: ");
                input = scanner.next().toUpperCase();

                return Category.valueOf(input);
            } catch (IllegalArgumentException iae) {
                System.out.println("Invalid.");
            }
        }
    }
}
