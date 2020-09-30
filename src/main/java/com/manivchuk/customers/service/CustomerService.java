package com.manivchuk.customers.service;

import com.manivchuk.customers.model.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Customer findById(Long id);

    Customer add(Customer customer);

    void delete(Long id);
}
