package at.htl.kaindorf.a107_legocollection.pojos;

public class Brick {
    private String title;
    private int elementNumber;
    private int designNumber;
    private String color;
    private float price;

    public Brick(String title, int elementNumber, int designNumber, String color, float price) {
        this.title = title;
        this.elementNumber = elementNumber;
        this.designNumber = designNumber;
        this.color = color;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getElementNumber() {
        return elementNumber;
    }

    public void setElementNumber(int elementNumber) {
        this.elementNumber = elementNumber;
    }

    public int getDesignNumber() {
        return designNumber;
    }

    public void setDesignNumber(int designNumber) {
        this.designNumber = designNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
