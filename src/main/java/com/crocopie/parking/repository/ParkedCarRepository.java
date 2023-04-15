package com.crocopie.parking.repository;

import com.crocopie.parking.model.ParkedCarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParkedCarRepository extends JpaRepository<ParkedCarEntity, UUID> {
}
