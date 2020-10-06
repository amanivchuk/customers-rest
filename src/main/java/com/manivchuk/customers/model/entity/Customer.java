package com.manivchuk.customers.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.manivchuk.customers.controller.Region;
import com.manivchuk.customers.model.entity.bill.Bill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 4, max = 24)
    @Column(nullable = false)
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty(message = "can't be empty")
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message="date can't be empty")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    private String photo;

    @NotNull(message = "Region can't be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region_id")
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @JsonIgnore
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    private Region region;

    @JsonIgnoreProperties(value = {"customer", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Bill> bills = new ArrayList<>();


    private static final long serialVersionUID = 1L;

//    @PrePersist
    public void prePersist(){
        createdAt = new Date();
    }
}
