package at.htlkaindorf._01_inventorymanagement.pojos;
import at.htlkaindorf._01_inventorymanagement.interfaces.SortByCode;

import java.util.Objects;

public class Item implements SortByCode {
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

    @Override
    public long getCode() {
        return code;
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
        return String.format("%-10d x %-7d: %s (%s)", amount, code, name, type.getName());
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
