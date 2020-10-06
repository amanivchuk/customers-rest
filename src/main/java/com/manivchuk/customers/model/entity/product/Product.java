package com.manivchuk.customers.model.entity.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @PrePersist
    public void prePersist(){
        this.createdAt = new Date();
    }


}
