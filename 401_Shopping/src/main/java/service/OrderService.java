package service;

import repository.OrderRepository;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService() {
        this.orderRepository = new OrderRepository();
    }

    public void initDB() {
        orderRepository.create();
    }
}
