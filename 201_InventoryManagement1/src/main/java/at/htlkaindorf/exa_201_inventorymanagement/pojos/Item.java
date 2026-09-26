package at.htlkaindorf.exa_201_inventorymanagement.pojos;

import at.htlkaindorf.exa_201_inventorymanagement.interfaces.SortBy;

import java.util.Locale;
import java.util.Objects;

public class Item implements SortBy {
    private Type type;
    private long code;
    private String name;
    private int amount;

    public Item(Type type, long code, String name, int amount) {
        this.type = type;
        this.code = code;
        this.name = name;
        this.amount = amount;
    }

    public long getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public void sortByCode() {

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return code == item.code;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }

    @Override
    public String toString() {
        return String.format(Locale.GERMAN, "%-10d x %d: %s (%s)", amount, code, name, type.getName());
    }
}
