package com.manivchuk.customers.model.entity.bill;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.manivchuk.customers.model.entity.Customer;
import com.manivchuk.customers.model.entity.itemFactura.ItemFactura;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String comment;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties(value = {"bills", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    private Customer customer;

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id")
    private List<ItemFactura> items = new ArrayList<>();

    public Double getTotal(){
        Double total = 0.00;
        for(ItemFactura item: items){
            total += item.getAmount();
        }
        return total;
    }

    @PrePersist
    public void prePersist(){
        this.createdAt = new Date();
    }
}
