package com.spring.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.models.TaxPayerModel;
import com.spring.repositories.TaxPayerRepository;

import ir.gov.tax.tpis.sdk.clients.TaxPublicApi;
import ir.gov.tax.tpis.sdk.cryptography.Signatory;
import ir.gov.tax.tpis.sdk.factories.Pkcs8SignatoryFactory;
import ir.gov.tax.tpis.sdk.factories.TaxApiFactory;
import ir.gov.tax.tpis.sdk.properties.TaxProperties;

@Service
public class TaxPayerService {
    private static final String API_URL = "https://tp.tax.gov.ir/requestsmanager";
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
        return taxPayerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TaxPayer not found with id: " + id));
    }

    public void updateTaxPayer(TaxPayerModel taxPayer) {
        if (taxPayerRepository.existsById(taxPayer.getId())) {
            taxPayerRepository.save(taxPayer);
        } else {
            throw new RuntimeException("TaxPayer not found with id: " + taxPayer.getId());
        }
    }

    public void activateTaxPayer(int id) {
        TaxPayerModel taxPayer = getTaxPayer(id);
        TaxProperties properties = new TaxProperties(taxPayer.getMemoryID());
        TaxApiFactory taxApiFactory = new TaxApiFactory(API_URL, properties);
        Pkcs8SignatoryFactory pkcs8SignatoryFactory = new Pkcs8SignatoryFactory();
        Signatory signatory = pkcs8SignatoryFactory.create(
                "PRIVATE_KEY_FILE",
                "CERTIFICATE_FILE");
        TaxPublicApi publicApi = taxApiFactory.createPublicApi(signatory);
        taxPayerRepository.save(taxPayer);
    }
}
