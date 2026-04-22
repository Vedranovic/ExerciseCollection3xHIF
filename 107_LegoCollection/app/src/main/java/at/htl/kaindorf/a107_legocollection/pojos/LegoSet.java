package at.htl.kaindorf.a107_legocollection.pojos;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class LegoSet {
    private ArrayList<BrickElement> elements;
    private String name;

    public LegoSet(String name) {
        this.elements = new ArrayList<>();
        this.name = name;
    }

    public void addBrick(Brick brick) {
        elements.add(new BrickElement(brick));
    }

    private int countBricks() {
        int sum = 0;

        for (BrickElement element : elements) {
            sum += element.getAmount();
        }

        return sum;
    }

    private float calcPrice() {
        float price = 0f;

        for (BrickElement element : elements) {
            price += element.getBrick().getPrice();
        }

        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LegoSet legoSet = (LegoSet) o;
        return Objects.equals(name, legoSet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return String.format(Locale.GERMAN, "%s with %d bricks\n%.2f €", name, countBricks(), calcPrice());
    }

    public ArrayList<BrickElement> getElements() {
        return elements;
    }

    public void setElements(ArrayList<BrickElement> elements) {
        this.elements = elements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
