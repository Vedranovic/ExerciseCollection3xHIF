import model.Category;
import service.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CategoryService categoryService = new CategoryService();
        ProductService productService = new ProductService();
        ProductOrderService productOrderService = new ProductOrderService();
        OrderService orderService = new OrderService();
        CustomerService customerService = new CustomerService();

//        Category software = categoryService.insert(new Category(1L, "Software"));
//        Category hardware = categoryService.insert(new Category(2L, "Hardware"));
//        Category laptop = categoryService.insert(new Category(3L, "Laptop"));

        categoryService.delete(4);
        categoryService.delete(5);
        categoryService.delete(6);

        List<Category> categories = categoryService.findAll();

        categories.forEach(System.out::println);
    }
}
