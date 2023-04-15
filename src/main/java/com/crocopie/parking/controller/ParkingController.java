package com.crocopie.parking.controller;

import com.crocopie.parking.dto.CarInfo;
import com.crocopie.parking.dto.ParkingSlot;
import com.crocopie.parking.dto.PriceInfo;
import com.crocopie.parking.enumerated.Size;
import com.crocopie.parking.service.ParkingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking/slots")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @GetMapping
    public List<ParkingSlot> getAvailableParkingSlots(@RequestParam("carSize") Size carSize) {
        return parkingService.getAvailableParkingSlots(carSize);
    }

    @PostMapping
    public ParkingSlot bookParkingSlot(@PathVariable int slotId, @Valid @RequestBody CarInfo carInfo) {
        return parkingService.bookParkingSlot(slotId, carInfo);
    }

    @DeleteMapping
    public PriceInfo leaveParkingSlot(@PathVariable int slotId) {
        return parkingService.leaveParkingSlot(slotId);
    }
}
