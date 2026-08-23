package service;

import repository.ProductRepository;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService() {
        this.productRepository = new ProductRepository();
    }

    public void initDB() {
        productRepository.create();
    }
}
