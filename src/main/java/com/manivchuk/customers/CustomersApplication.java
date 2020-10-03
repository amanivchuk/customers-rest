package com.manivchuk.customers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomersApplication {

//    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public static void main(String[] args) {
        SpringApplication.run(CustomersApplication.class, args);
    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        String password = "admin";
//
//        for(int i = 0; i < 4; i++){
//            String passwordBcrypt =  passwordEncoder.encode(password);
//            System.out.println(passwordBcrypt);
//        }
//    }
}
