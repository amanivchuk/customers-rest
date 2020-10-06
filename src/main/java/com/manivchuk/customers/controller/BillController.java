package com.manivchuk.customers.controller;

import com.manivchuk.customers.model.entity.bill.Bill;
import com.manivchuk.customers.model.entity.product.Product;
import com.manivchuk.customers.model.repository.BillRepository;
import com.manivchuk.customers.service.CustomerService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@Setter(onMethod_ = @Autowired)
public class BillController {

    private CustomerService customerService;

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/bill/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Bill find(@PathVariable Long id){
        return customerService.findBillById(id);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/bill/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        customerService.deleteBillBuId(id);
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/bill/filter-products/{name}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Product> filterProducts(@PathVariable String name){
        return customerService.findProductByName(name);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/bill")
    @ResponseStatus(HttpStatus.CREATED)
    public Bill create(@RequestBody Bill bill){
        return customerService.saveBill(bill);
    }

}
