package com.spring.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.enums.StuffIdType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class StuffIdModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 13, unique = true, nullable = false)
    private String stuffId;
    @Column(length = 1023, nullable = false)
    private String name;
    private int vatRate;
    @Column(nullable = false)
    private boolean privateId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StuffIdType stuffIdType;
    private LocalDate registrationDate;
    @ManyToMany(mappedBy = "stuffIdSet")
    @JsonIgnore
    private Set<TaxPayerModel> taxPayerSet = new HashSet<>();
}
