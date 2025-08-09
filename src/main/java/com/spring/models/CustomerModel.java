package com.spring.models;

import com.spring.enums.CustomerType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class CustomerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean active;
    private boolean customer;
    private boolean provider;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CustomerType customerType;
    private String identificationCode;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String nationalId;
    @Column(nullable = false)
    private String economicCode;
    private String registrationNumber;
    private String phoneNumber;
    private String faxNumber;
    private String mobileNumber;
    private String postalCode;
    private String province;
    private String city;
    private String address;
    private String email;
    private String passportNumber;
}
