package com.manivchuk.customers.model.repository;

import com.manivchuk.customers.controller.Region;
import com.manivchuk.customers.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("from Region ")
    List<Region> findAllRegions();
}
