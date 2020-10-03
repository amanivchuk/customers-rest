package com.manivchuk.customers.model.repository;

import com.manivchuk.customers.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    @Query("select u from User u where u.username=?1")
    User findByUsername2(String username);

}
