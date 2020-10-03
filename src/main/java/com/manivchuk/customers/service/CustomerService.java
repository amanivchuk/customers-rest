package com.manivchuk.customers.service;

import com.manivchuk.customers.controller.Region;
import com.manivchuk.customers.model.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();

    Page<Customer> findAll(Pageable pageable);

    Customer findById(Long id);

    Customer add(Customer customer);

    void delete(Long id);

    List<Region> findAllRegions();
}
