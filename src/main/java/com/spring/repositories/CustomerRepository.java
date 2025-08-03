package com.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.models.CustomerModel;

public interface CustomerRepository extends JpaRepository<CustomerModel, Integer> {
}
