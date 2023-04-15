package com.crocopie.parking.service;

import com.crocopie.parking.dto.CarInfo;
import com.crocopie.parking.dto.ParkingSlot;
import com.crocopie.parking.dto.PriceInfo;
import com.crocopie.parking.enumerated.Size;

import java.util.List;

public interface ParkingService {
    List<ParkingSlot> getAvailableParkingSlots(Size carSize);
    ParkingSlot bookParkingSlot(int slotId, CarInfo carInfo);
    PriceInfo leaveParkingSlot(int slotId);
}
