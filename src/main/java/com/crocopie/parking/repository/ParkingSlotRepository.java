package com.crocopie.parking.repository;

import com.crocopie.parking.enumerated.Size;
import com.crocopie.parking.enumerated.SlotStatus;
import com.crocopie.parking.model.ParkingSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlotEntity, Integer> {
    List<ParkingSlotEntity> findAllBySizeInAndStatusNot(List<Size> sizes, SlotStatus status);
}
