package com.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.models.StuffIdModel;

public interface StuffIdRepository extends JpaRepository<StuffIdModel, Integer> {
}
