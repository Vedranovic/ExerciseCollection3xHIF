package pojos;

import Enum.Category;

import java.util.Locale;

public class Product {
    private Category category;
    private int id;
    private String name;
    private double price;
    private int stock;
    private boolean organic;

    public Product(Category category, int id, String name, double price, int stock, boolean organic) {
        this.category = category;
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.organic = organic;
    }

    @Override
    public String toString() {
        return String.format(Locale.GERMAN, "#%02d %-8s %15s %10.2f € stock=%3d bio=%s", id, name, category, price, stock, organic ? 'Y' : 'N');
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public boolean isOrganic() {
        return organic;
    }
}
