package at.htl.kaindorf.a107_legocollection.pojos;

public class BrickElement {
    private Brick brick;
    private int amount;

    public BrickElement(Brick brick) {
        this.brick = brick;
        this.amount = 0;
    }

    public void increaseAmount() {
        amount++;
    }

    public void decreaseAmount() throws Exception {
        if (amount < 1) {
            throw new Exception("Amount cannot be less than zero");
        }

        amount--;
    }

    public Brick getBrick() {
        return brick;
    }

    public void setBrick(Brick brick) {
        this.brick = brick;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}