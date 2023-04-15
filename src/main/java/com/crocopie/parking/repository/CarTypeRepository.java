package com.crocopie.parking.repository;

import com.crocopie.parking.model.CarTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarTypeRepository extends JpaRepository<CarTypeEntity, Integer> {
}
