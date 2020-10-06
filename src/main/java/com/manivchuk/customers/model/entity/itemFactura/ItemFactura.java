package com.manivchuk.customers.model.entity.itemFactura;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.manivchuk.customers.model.entity.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "facturas_items")
public class ItemFactura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Double getAmount(){
        return quantity.doubleValue() * product.getPrice();
    }

    private static final long serialVersionUID = 1L;
}
