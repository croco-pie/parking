package com.crocopie.parking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "parked_car")
@Getter
@Setter
@NoArgsConstructor
public class ParkedCarEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "parking_slot_id")
    private ParkingSlotEntity parkingSlotId;
    private String carRegNumber;
    @ManyToOne
    @JoinColumn(name = "car_type_id")
    private CarTypeEntity carTypeId;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;
    private BigDecimal totalPrice;
    private Boolean serviceDone;

}
