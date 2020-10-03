package com.manivchuk.customers.controller;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.manivchuk.customers.model.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "regions")
public class Region implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "region")
//    private List<Customer> customers = new ArrayList<>();

    private static final long serialVersionUID = 1L;
}
