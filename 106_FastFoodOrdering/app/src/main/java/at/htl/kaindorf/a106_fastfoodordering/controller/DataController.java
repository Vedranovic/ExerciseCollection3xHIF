package at.htl.kaindorf.a106_fastfoodordering.controller;

import java.util.ArrayList;
import java.util.Comparator;

import at.htl.kaindorf.a106_fastfoodordering.pojos.FoodItem;

public class DataController {
    private ArrayList<FoodItem> items;

    public DataController() {
        this.items = new ArrayList<>();
    }

    public void sort(String type) {
        switch (type) {
            case "ASC":
                items.sort(new Comparator<FoodItem>() {
                    @Override
                    public int compare(FoodItem item1, FoodItem item2) {
                        return item1.getName().compareTo(item2.getName());
                    }
                });
                break;
            case "DESC":
                items.sort(new Comparator<FoodItem>() {
                    @Override
                    public int compare(FoodItem item1, FoodItem item2) {
                        return item2.getName().compareTo(item1.getName());
                    }
                });
                break;
        }
    }

    public void addItem(FoodItem item) {
        items.add(item);
    }

    public double getFullPrice() {
        double price = 0.0;

        for (FoodItem item : items) {
            price += item.getPrice() * item.getAmount();
        }

        return price;
    }

    public ArrayList<FoodItem> getItems() {
        return items;
    }
}
