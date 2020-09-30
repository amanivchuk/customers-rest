package com.manivchuk.customers.model.repository;

import com.manivchuk.customers.model.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
