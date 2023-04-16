package com.crocopie.parking.repository;

import com.crocopie.parking.model.ParkedCarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ParkedCarRepository extends JpaRepository<ParkedCarEntity, UUID> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM parked_car pc WHERE pc.parking_slot_id IN (?1) AND pc.service_done = ?2")
    List<ParkedCarEntity> findAllByParkingSlotIdInAndServiceDone(List<Integer> ids, Boolean serviceDone);
}
