package com.spring.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.models.TaxPayerModel;
import com.spring.repositories.TaxPayerRepository;

@Service
public class TaxPayerService {
    private TaxPayerRepository taxPayerRepository;
    public TaxPayerService(TaxPayerRepository taxPayerRepository) {
        this.taxPayerRepository = taxPayerRepository;
    }

    public List<TaxPayerModel> getAllTaxPayers() {
        return taxPayerRepository.findAll();
    }
    public void createTaxPayer(TaxPayerModel taxPayer) {
        taxPayerRepository.save(taxPayer);
    }
    public TaxPayerModel getTaxPayer(int id) {
        return taxPayerRepository.findById(id).
            orElseThrow(() -> new RuntimeException("TaxPayer not found with id: " + id));
    }
}
