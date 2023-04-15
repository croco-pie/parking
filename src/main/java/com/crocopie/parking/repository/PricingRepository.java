package com.crocopie.parking.repository;

import com.crocopie.parking.model.PricingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricingRepository extends JpaRepository<PricingEntity, Integer> {
}
