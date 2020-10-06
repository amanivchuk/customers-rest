package com.manivchuk.customers.model.repository;

import com.manivchuk.customers.model.entity.product.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("select p from Product p where p.name like %?1%")
    List<Product> findByName(String name);

//    List<Product> findByNameContainingIgnoreCase(String  name);
//
//    List<Product> findByNameStartWithIgnoreCase(String  name);

}
