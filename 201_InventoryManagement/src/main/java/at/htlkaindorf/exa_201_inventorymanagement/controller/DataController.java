package at.htlkaindorf.exa_201_inventorymanagement.controller;

import at.htlkaindorf.exa_201_inventorymanagement.pojos.Item;
import at.htlkaindorf.exa_201_inventorymanagement.pojos.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Comparator;

public class DataController {
    private ObservableList<Type> typesList;
    private ObservableList<Item> itemsList;
    private ObservableList<Item> filteredItemList;

    public DataController() {
        typesList = FXCollections.observableArrayList();
        itemsList = FXCollections.observableArrayList();
        filteredItemList = FXCollections.observableArrayList();
    }

    public void addType(Type type) throws Exception {
        if (typesList.contains(type)) {
            throw new Exception("This type id is already in use!");
        }

        typesList.add(type);
        typesList.sort(Comparator.comparing(Type::getName));
    }

    public void removeType(int index) {
        typesList.remove(index);
        typesList.sort(Comparator.comparing(Type::getName));
    }

    public void addItem(Item item) throws Exception {
        if (itemsList.contains(item)) {
            throw new Exception("This item is already in the list!");
        }

        itemsList.add(item);
        // TODO: itemsList sortieren
    }

    public void removeItem(int index) {
        itemsList.remove(index);
        // TODO: itemsList sortieren
    }

    public void filterItems(String searchText) {
        if (searchText.isBlank()) {
            filteredItemList.addAll(itemsList);
        }

        for (Item item : itemsList) {
            String itemText = item.getCode() + item.getName().toLowerCase();

            if (itemText.contains(searchText.toLowerCase())) {
                filteredItemList.add(item);
            }
        }
    }

    public void changeAmountOfFilteredItems(int amount, int index) {
        filteredItemList.get(index).setAmount(amount);

        for (Item item : itemsList) {
            if (item.equals(filteredItemList.get(index))) {
                item.setAmount(amount);
                break;
            }
        }
    }

    public ObservableList<Type> getTypesList() {
        return typesList;
    }

    public ObservableList<Item> getItemsList() {
        return itemsList;
    }

    public ObservableList<Item> getFilteredItemList() {
        return filteredItemList;
    }
}
