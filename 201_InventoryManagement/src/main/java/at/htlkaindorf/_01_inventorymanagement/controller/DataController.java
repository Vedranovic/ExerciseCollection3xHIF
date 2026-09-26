package at.htlkaindorf._01_inventorymanagement.controller;

import at.htlkaindorf._01_inventorymanagement.comparator.TypeComparator;
import at.htlkaindorf._01_inventorymanagement.interfaces.SortByCode;
import at.htlkaindorf._01_inventorymanagement.pojos.Item;
import at.htlkaindorf._01_inventorymanagement.pojos.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Comparator;

public class DataController {
    private ObservableList<Type> typesList;
    private ObservableList<Item> itemsList;
    private ObservableList<Item> filteredList;

    public DataController() {
        typesList = FXCollections.observableArrayList();
        itemsList = FXCollections.observableArrayList();
        filteredList = FXCollections.observableArrayList();
    }

    public void addType(Type type) throws Exception {
        if (typesList.contains(type)) {
            throw new Exception("This type id is already in use!");
        }

        typesList.add(type);
        typesList.sort(new TypeComparator());
    }

    public void removeType(int index) {
        typesList.remove(index);
    }

    public void addItem(Item item) throws Exception {
        if (itemsList.contains(item)) {
            throw new Exception("This item is already in the list!");
        }

        itemsList.add(item);
        itemsList.sort(new Comparator<SortByCode>() {
            @Override
            public int compare(SortByCode o1, SortByCode o2) {
                return Long.compare(o1.getCode(), o2.getCode());
            }
        });
    }

    public void removeItem(int index) {
        itemsList.remove(index);
    }

    public void filterItems(String searchText) {
        filteredList.clear();

        for (Item item : itemsList) {
            if (item.toString().contains(searchText)) {
                filteredList.add(item);
            }
        }
    }

    public void changeAmountOfFiltered(int amount, int index) {
        itemsList.get(index).setAmount(amount);
    }

    public ObservableList<Type> getTypesList() {
        return typesList;
    }

    public ObservableList<Item> getItemsList() {
        return itemsList;
    }

    public ObservableList<Item> getFilteredList() {
        return filteredList;
    }
}
