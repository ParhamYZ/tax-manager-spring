package com.spring.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.models.StuffIdModel;
import com.spring.models.TaxPayerModel;
import com.spring.repositories.StuffIdRepository;
import com.spring.repositories.TaxPayerRepository;

import ir.gov.tax.tpis.sdk.content.api.DefaultTaxApiClient;
import ir.gov.tax.tpis.sdk.content.api.TaxApi;
import ir.gov.tax.tpis.sdk.content.dto.ServerInformationModel;
import ir.gov.tax.tpis.sdk.transfer.api.ObjectTransferApiImpl;
import ir.gov.tax.tpis.sdk.transfer.api.TransferApi;
import ir.gov.tax.tpis.sdk.transfer.config.ApiConfig;
import ir.gov.tax.tpis.sdk.transfer.impl.signatory.SignatoryFactory;

@Service
public class TaxPayerService {
    private TaxPayerRepository taxPayerRepository;
    private StuffIdRepository stuffIdRepository;

    public TaxPayerService(TaxPayerRepository taxPayerRepository, StuffIdRepository stuffIdRepository) {
        this.taxPayerRepository = taxPayerRepository;
        this.stuffIdRepository = stuffIdRepository;
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

    public void updateTaxPayer(int id, TaxPayerModel taxPayer) {
        TaxPayerModel existing = taxPayerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TaxPayer not found with id: " + id));
        BeanUtils.copyProperties(taxPayer, existing, "id"); // skips id
        taxPayerRepository.save(existing);
    }

    public void deleteTaxPayer(int id) {
        TaxPayerModel existing = taxPayerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TaxPayer not found with id: " + id));
        taxPayerRepository.delete(existing);
    }

    @Transactional
    public void addStuffIdToTaxPayer(int taxPayerId, int stuffId) {
        TaxPayerModel taxPayerModel = taxPayerRepository.findById(taxPayerId)
                .orElseThrow(() -> new RuntimeException("TaxPayer not found with id: " + taxPayerId));
        StuffIdModel stuffIdModel = stuffIdRepository.findById(stuffId)
                .orElseThrow(() -> new RuntimeException("StuffId not found with id: " + stuffId));
        
        taxPayerModel.getStuffIdSet().add(stuffIdModel);
        taxPayerRepository.save(taxPayerModel);
    }

    public void getServerInformation() {
        // final String URL = "private_key.pem";
        final String MEMORY_ID = "A2WGP4";

        ApiConfig apiConfig = null;
        try {
            apiConfig = new ApiConfig().transferSignatory(
                    SignatoryFactory.getInstance().createPKCS8Signatory(
                            new File("../Private.txt"), null));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        TransferApi transferApi = new ObjectTransferApiImpl(apiConfig);
        TaxApi taxApi = new DefaultTaxApiClient(transferApi, MEMORY_ID);
        ServerInformationModel serverInformation = taxApi.getServerInformation();
        System.out.println("Server information: " + serverInformation);
    }
}
