package com.spring.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.models.TaxPayerModel;
import com.spring.services.TaxPayerService;

@RestController
@RequestMapping("/api/v1/tax-payers")
public class TaxPayerController {
    private final TaxPayerService taxPayerService;

    public TaxPayerController(TaxPayerService taxPayerService) {
        this.taxPayerService = taxPayerService;
    }

    @GetMapping
    public List<TaxPayerModel> getTaxPayers() {
        return taxPayerService.getAllTaxPayers();
    }

    @GetMapping("{id}")
    public TaxPayerModel getTaxPayer(
            @PathVariable int id) {
        return taxPayerService.getTaxPayer(id);
    }

    @PostMapping
    public void createTaxPayer(
            @RequestBody TaxPayerModel taxPayer) {
        taxPayerService.createTaxPayer(taxPayer);
    }

    @PutMapping
    public void updateTaxPayer(
            @RequestBody TaxPayerModel taxPayer) {
        taxPayerService.updateTaxPayer(taxPayer);
    }

    @PostMapping("{id}/activate")
    public void activateTaxPayer(
        @PathVariable int id) {
        taxPayerService.activateTaxPayer(id);
    }
}
