package at.htlkaindorf.exa_201_inventorymanagement.pojos;

import java.util.Locale;
import java.util.Objects;

public class Type {
    private long id;
    private String name;

    public Type(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return id == type.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format(Locale.GERMAN, "%04d: %s", id, name);
    }
}
