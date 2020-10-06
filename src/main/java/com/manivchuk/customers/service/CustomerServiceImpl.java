package com.manivchuk.customers.service;

import com.manivchuk.customers.controller.Region;
import com.manivchuk.customers.model.entity.Customer;
import com.manivchuk.customers.model.entity.bill.Bill;
import com.manivchuk.customers.model.entity.product.Product;
import com.manivchuk.customers.model.repository.BillRepository;
import com.manivchuk.customers.model.repository.CustomerRepository;
import com.manivchuk.customers.model.repository.ProductRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Setter(onMethod_ = @Autowired)
public class CustomerServiceImpl implements CustomerService{

    private CustomerRepository customerRepository;
    private BillRepository billRepository;
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer add(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Region> findAllRegions() {
        return customerRepository.findAllRegions();
    }

    @Override
    @Transactional(readOnly = true)
    public Bill findBillById(Long id) {
        return billRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Bill saveBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public void deleteBillBuId(Long id) {
        billRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findProductByName(String name) {
        return productRepository.findByName(name);
    }
}
