package com.spring.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    private String memoryId;
    @Column(nullable = false)
    private String privateKey;
    @ManyToMany
    @JoinTable(name = "taxpayer_stuffid", joinColumns = @JoinColumn(name = "taxpayer_id"), inverseJoinColumns = @JoinColumn(name = "stuffid_id") // FK
                                                                                                                                                 // to
                                                                                                                                                 // Product
    )
    private Set<StuffIdModel> stuffIdSet = new HashSet<>();
}
