package com.manivchuk.customers.controller;

import com.manivchuk.customers.model.entity.Customer;
import com.manivchuk.customers.service.CustomerService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
@Setter(onMethod_ = @Autowired)
public class CustomerController {

    private CustomerService customerService;

    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/customers/page/{page}")
    @ResponseStatus(HttpStatus.OK)
    public Page<Customer> findAll(@PathVariable Integer page) {
        PageRequest pageable = PageRequest.of(page, 4);
        return customerService.findAll(pageable);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        Customer customer = null;
        Map<String, Object> response = new HashMap<>();

        try {
            customer = customerService.findById(id);
        } catch (DataAccessException e) {
            response.put("message", "Database not available");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (customer == null) {
            response.put("message", "Customer with id: ".concat(id.toString()).concat(" not found"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> add(@Valid @RequestBody Customer customer, BindingResult result) {

        Customer newCustomer = null;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "Field '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            newCustomer = customerService.add(customer);
        } catch (DataAccessException e) {
            response.put("message", "Database not available");
            response.put("errors", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "New customer created");
        response.put("customer", newCustomer);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@Valid @RequestBody Customer customer, @PathVariable Long id, BindingResult result) {
        Customer customerActual = customerService.findById(id);
        Customer updatedCustomer = null;
        Map<String, Object> response = new HashMap<>();

        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "Field '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }


        if (customerActual == null) {
            response.put("message", "Error no data for update");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            customerActual.setFirstName(customer.getFirstName());
            customerActual.setLastName(customer.getLastName());
            customerActual.setEmail(customer.getEmail());
            updatedCustomer = customerService.add(customerActual);
        } catch (DataAccessException e) {
            response.put("message", "Database not available");
            response.put("errors", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Customer updated");
        response.put("customer", updatedCustomer);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            customerService.delete(id);
        } catch (DataAccessException e) {
            response.put("message", "Database not available");
            response.put("errors", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Customer deleted");

        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.OK);
    }
}
