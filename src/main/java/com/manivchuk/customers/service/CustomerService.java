package com.manivchuk.customers.service;

import com.manivchuk.customers.controller.Region;
import com.manivchuk.customers.model.entity.Customer;
import com.manivchuk.customers.model.entity.bill.Bill;
import com.manivchuk.customers.model.entity.product.Product;
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

    Bill findBillById(Long id);

    Bill saveBill(Bill bill);

    void deleteBillBuId(Long id);

    List<Product> findProductByName(String name);
}
