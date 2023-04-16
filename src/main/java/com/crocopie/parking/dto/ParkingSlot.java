package com.crocopie.parking.dto;

import com.crocopie.parking.enumerated.Size;
import com.crocopie.parking.enumerated.SlotStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingSlot {

    private int id;
    private Size size;
    private SlotStatus slotStatus;

}
