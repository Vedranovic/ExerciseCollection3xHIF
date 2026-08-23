package service;

import repository.ProductOrderRepository;

public class ProductOrderService {
    private final ProductOrderRepository productOrderRepository;

    public ProductOrderService() {
        this.productOrderRepository = new ProductOrderRepository();
    }

    public void initDB() {
        productOrderRepository.create();
    }
}
