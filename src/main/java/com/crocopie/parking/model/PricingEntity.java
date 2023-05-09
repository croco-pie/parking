package com.crocopie.parking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "pricing")
@Getter
@Setter
@NoArgsConstructor
public class PricingEntity {

    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private CarTypeEntity carTypeId;
    private Integer periodInMin;
    private Integer periodSequenceNum;
    private BigDecimal price;

}
