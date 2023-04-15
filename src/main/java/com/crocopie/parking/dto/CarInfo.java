package com.crocopie.parking.dto;

import com.crocopie.parking.enumerated.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class CarInfo {

    @NotNull
    private Size carSize;
    @NotBlank
    private String carRegistrationNumber;

}
