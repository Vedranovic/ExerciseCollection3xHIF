package at.htl.kaindorf.a106_fastfoodordering.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Locale;

public class FoodItem implements Parcelable {
    private int amount;
    private String name;
    private int kcal;
    private double price;

    public FoodItem(String name, int kcal, double price) {
        this.amount = 1;
        this.name = name;
        this.kcal = kcal;
        this.price = price;
    }

    protected FoodItem(Parcel in) {
        amount = in.readInt();
        name = in.readString();
        kcal = in.readInt();
        price = in.readDouble();
    }

    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };

    public void increaseAmount() {
        amount++;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(amount);
        parcel.writeString(name);
        parcel.writeInt(kcal);
        parcel.writeDouble(price);
    }

    @Override
    public String toString() {
        return String.format(Locale.GERMAN, "%d x %s\n(%.2f €)", getAmount(), getName(), getPrice() * getAmount());
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
}
