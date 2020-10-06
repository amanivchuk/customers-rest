package com.manivchuk.customers.model.repository;

import com.manivchuk.customers.model.entity.bill.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill, Long> {
}
