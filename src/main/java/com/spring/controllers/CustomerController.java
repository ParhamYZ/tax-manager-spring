package com.spring.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.enums.CustomerType;
import com.spring.models.CustomerModel;
import com.spring.services.CustomerService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerModel> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerModel getCustomer(
            @PathVariable int id) {
        return customerService.getCustomer(id);
    }

    @PostMapping
    public void createCustomer(
            @RequestBody CustomerModel taxPayer) {
        customerService.createCustomer(taxPayer);
    }

    @PutMapping("/{id}")
    public void updateCustomer(
            @PathVariable int id,
            @RequestBody CustomerModel customer) {
        customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(
            @PathVariable int id) {
        customerService.deleteCustomer(id);
    }

    @GetMapping("/customer-types")
    public CustomerType[] getCustomerTypes() {
        return CustomerType.values();
    }
}
