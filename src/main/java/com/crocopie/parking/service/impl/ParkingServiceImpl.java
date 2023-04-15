package com.crocopie.parking.service.impl;

import com.crocopie.parking.dto.CarInfo;
import com.crocopie.parking.dto.ParkingSlot;
import com.crocopie.parking.dto.PriceInfo;
import com.crocopie.parking.enumerated.Size;
import com.crocopie.parking.service.ParkingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParkingServiceImpl implements ParkingService {

    @Override
    public List<ParkingSlot> getAvailableParkingSlots(Size carSize) {
        return null;
    }

    @Override
    public ParkingSlot bookParkingSlot(int slotId, CarInfo carInfo) {
        return null;
    }

    @Override
    public PriceInfo leaveParkingSlot(int slotId) {
        return null;
    }
}
