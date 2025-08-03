package com.spring.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.spring.models.CustomerModel;
import com.spring.repositories.CustomerRepository;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerModel> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void createCustomer(CustomerModel customer) {
        customerRepository.save(customer);
    }

    public CustomerModel getCustomer(int id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    public void updateCustomer(int id, CustomerModel customer) {
        CustomerModel existing = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        BeanUtils.copyProperties(customer, existing, "id"); // skips id
        customerRepository.save(existing);
    }

    public void deleteCustomer(int id) {
        CustomerModel existing = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        customerRepository.delete(existing);
    }
}
