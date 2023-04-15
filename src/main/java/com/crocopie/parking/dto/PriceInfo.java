package com.crocopie.parking.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PriceInfo {

    private int totalTimeHours;
    private int totalTimeMinutes;
    private BigDecimal totalPrice;
    private String carRegistrationNumber;

}
