package com.codegym.service;

import com.codegym.model.Customer;


public interface CustomerService {
    Iterable<Customer> findAll();

    Customer save(Customer customer);

    void remove(Long id);

    Customer findById(Long id);

}
