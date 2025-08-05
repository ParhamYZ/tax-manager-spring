package com.spring.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tax_payer_model")
public class TaxPayerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean legal;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String nationalId;
    @Column(nullable = false)
    private String economicCode;
    private String registrationNumber;
    @Column(nullable = true)
    private int invoiceSerialNumberBeginning;
    @Column(nullable = true)
    private int reactionaryInvoiceSerialNumberBeginning;
    private String phoneNumber;
    private String faxNumber;
    @Column(nullable = false)
    private String postalCode;
    private String province;
    private String city;
    private String address;
    @Column(nullable = false)
    private String memoryID;
    @Column(nullable = false)
    private String privateKey;
}

