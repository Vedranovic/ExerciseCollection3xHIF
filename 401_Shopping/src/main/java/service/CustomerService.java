package service;

import repository.CustomerRepository;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService() {
        this.customerRepository = new CustomerRepository();
    }

    public void initDB() {
        customerRepository.create();
    }
}
