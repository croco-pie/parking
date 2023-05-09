package com.crocopie.parking.service.impl;

import com.crocopie.parking.dto.CarInfo;
import com.crocopie.parking.dto.ParkingSlot;
import com.crocopie.parking.dto.PriceInfo;
import com.crocopie.parking.enumerated.Size;
import com.crocopie.parking.enumerated.SlotStatus;
import com.crocopie.parking.model.ParkedCarEntity;
import com.crocopie.parking.model.ParkingSlotEntity;
import com.crocopie.parking.repository.ParkedCarRepository;
import com.crocopie.parking.repository.ParkingSlotRepository;
import com.crocopie.parking.service.ParkingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ParkingServiceImpl implements ParkingService {

    private final ParkingSlotRepository parkingSlotRepository;
    private final ParkedCarRepository parkedCarRepository;

    @Override
    public List<ParkingSlot> getAvailableParkingSlots(Size carSize) {
        var maybeAvailableSlots = getMaybeAvailableSlots(carSize);

        var availableSlots = geFullyAvailableSlots(maybeAvailableSlots, carSize);
        var partiallyAvailableSlots = getPartiallyAvailableSlots(maybeAvailableSlots, carSize);

        if (!partiallyAvailableSlots.isEmpty()) {
            availableSlots.addAll(partiallyAvailableSlots);
        }

        return mapAndSortParkingSlots(availableSlots);
    }

    @Override
    public ParkingSlot bookParkingSlot(int slotId, CarInfo carInfo) {
        return null;
    }

    @Override
    public PriceInfo leaveParkingSlot(int slotId) {
        return null;
    }

    private List<ParkingSlotEntity> getMaybeAvailableSlots(Size carSize) {
        var appropriateSlotSizes = getAppropriateSlotSizes(carSize);
        return parkingSlotRepository.findAllBySizeInAndStatusNot(appropriateSlotSizes, SlotStatus.OCCUPIED);
    }

    private List<Size> getAppropriateSlotSizes(Size carSize) {
        return Arrays.stream(Size.values())
                .filter(slotSize -> slotSize.getCapacity() >= carSize.getCapacity())
                .toList();
    }

    private List<ParkingSlotEntity> geFullyAvailableSlots(List<ParkingSlotEntity> slots, Size carSize) {
        return slots.stream()
                .filter(slot -> slot.getStatus() == SlotStatus.AVAILABLE
                        && slot.getSize().getCapacity() >= carSize.getCapacity())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private List<ParkingSlotEntity> getPartiallyAvailableSlots(List<ParkingSlotEntity> slots, Size carSize) {
        var needCheckAvailabilitySlots = getNeedCheckAvailabilityForLargerSlots(slots, carSize);

        Map<ParkingSlotEntity, ArrayList<Size>> maybePartiallyAvailableSlots =
                getMappedMaybePartiallyAvailableSlots(needCheckAvailabilitySlots);

        return calculatePartiallyAvailableSlots(maybePartiallyAvailableSlots, carSize);
    }

    private List<ParkingSlotEntity> getNeedCheckAvailabilityForLargerSlots(List<ParkingSlotEntity> slots, Size carSize) {
        return slots.stream()
                .filter(slot -> slot.getStatus() == SlotStatus.PARTIALLY_AVAILABLE
                        && slot.getSize().getCapacity() > carSize.getCapacity())
                .toList();
    }

    private Map<ParkingSlotEntity, ArrayList<Size>> getMappedMaybePartiallyAvailableSlots(
            List<ParkingSlotEntity> needCheckAvailabilitySlots) {
        Map<ParkingSlotEntity, ArrayList<Size>> maybePartiallyAvailableSlots = new HashMap<>();
        var needCheckAvailabilitySlotIds = needCheckAvailabilitySlots.stream().map(ParkingSlotEntity::getId).toList();

        needCheckAvailabilitySlots.forEach(slot -> maybePartiallyAvailableSlots.put(slot, new ArrayList<>()));

        var needCheckParkedCars =
                parkedCarRepository.findAllByParkingSlotIdInAndServiceDone(needCheckAvailabilitySlotIds, false);

        for (ParkedCarEntity parkedCar : needCheckParkedCars) {
            maybePartiallyAvailableSlots.get(parkedCar.getParkingSlotId())
                    .add(parkedCar.getCarTypeId().getSize());
        }

        return maybePartiallyAvailableSlots;
    }

    private List<ParkingSlotEntity> calculatePartiallyAvailableSlots(
            Map<ParkingSlotEntity, ArrayList<Size>> mappedPartiallyAvailableSlots,
            Size carSize
    ) {
        List<ParkingSlotEntity> partiallyAvailableSlots = new ArrayList<>();
        mappedPartiallyAvailableSlots.forEach(
                (slot, sizes) -> {
                    int sum = 0;
                    for (Size size : sizes) {
                        sum += size.getCapacity();
                    }

                    if (slot.getSize().getCapacity() - sum >= carSize.getCapacity()) {
                        partiallyAvailableSlots.add(slot);
                    }
                }
        );

        return partiallyAvailableSlots;
    }

    private List<ParkingSlot> mapAndSortParkingSlots(List<ParkingSlotEntity> slots) {
        return slots.stream()
                .map(this::mapEntityToDto)
                .sorted(Comparator.comparingInt(ParkingSlot::getId))
                .toList();
    }

    private ParkingSlot mapEntityToDto(ParkingSlotEntity entity) {
        return ParkingSlot.builder()
                .id(entity.getId())
                .size(entity.getSize())
                .slotStatus(entity.getStatus())
                .build();
    }
}
