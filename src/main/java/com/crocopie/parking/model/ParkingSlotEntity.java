package com.crocopie.parking.model;

import com.crocopie.parking.enumerated.Size;
import com.crocopie.parking.enumerated.SlotStatus;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "parking_slot")
@Getter
@Setter
@NoArgsConstructor
public class ParkingSlotEntity {

    @Id
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType.class)
    private Size size;
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType.class)
    private SlotStatus status;

}
