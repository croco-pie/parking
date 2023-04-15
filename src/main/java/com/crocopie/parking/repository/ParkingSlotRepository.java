package com.crocopie.parking.repository;

import com.crocopie.parking.model.ParkingSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlotEntity, Integer> {
}
