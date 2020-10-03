package com.manivchuk.customers.service;

import com.manivchuk.customers.model.entity.User;

public interface UserService {

    User findByUsername(String username);
}
