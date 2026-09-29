package pojos;

import Interface.ProductRule;

import java.util.ArrayList;
import java.util.List;

public class ProductTools {
    public static List<Product> filter(List<Product> source, ProductRule rule) {
        List<Product> filteredList = new ArrayList<>();

        for (Product product : source) {
            if (rule.test(product)) {
                filteredList.add(product);
            }
        }

        return filteredList;
    }

    public static void printAll(List<Product> list) {
        for (Product product : list) {
            System.out.println(product);
        }

        System.out.println();
    }
}
