package com.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.models.TaxPayerModel;

public interface TaxPayerRepository extends JpaRepository<TaxPayerModel, Integer> {
}
