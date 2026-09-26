package at.htlkaindorf._01_inventorymanagement.comparator;

import at.htlkaindorf._01_inventorymanagement.pojos.Type;

import java.util.Comparator;

public class TypeComparator implements Comparator<Type> {
    @Override
    public int compare(Type o1, Type o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
