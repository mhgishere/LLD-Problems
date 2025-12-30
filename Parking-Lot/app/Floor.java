package app;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Floor {
    int floorNumber;
    ParkingLot parkingLot;
    List<Slot> slots;

    public Floor(int floorNumber, ParkingLot parkingLot) {
        this.floorNumber = floorNumber;
        this.parkingLot = parkingLot;

        int numberOfSlots = parkingLot.numberOfSlotsPerFloor;
        slots = new ArrayList<>(numberOfSlots);
        for(int i = 0; i < numberOfSlots; i++) {
            slots.add(i, new Slot(i, this));
        }
    }
    
    // We will assume that the first slot on each floor will be for a truck, 
    // the next 2 for bikes, and all the other slots for cars.
    Optional<Slot> fetchAvailableSlot(VehicleType vehicleType) {
        for(int i = 0; i < slots.size(); i++) {
            Slot slot = slots.get(i);
            if(slot.isSlotAvailable() && slot.isSlotCompatible(vehicleType)) {
                return Optional.of(slot);
            }
        }
        return Optional.empty();
    }

    List<Integer> fetchSlotsMatching(VehicleType vehicleType, Predicate<Slot> predicate) {
        List<Integer> slotsList = new ArrayList<>();
        for (int i = 0; i < slots.size(); i++) {
            Slot slot = slots.get(i);
            if (predicate.test(slot) && slot.isSlotCompatible(vehicleType)) {
                slotsList.add(i);
            }
        }
        return slotsList;
    }

    int fetchNumberOfFreeSlots(VehicleType vehicleType) {
        int answer = 0;
        for(int i = 0; i < slots.size(); i++) {
            Slot slot = slots.get(i);
            if(slot.isSlotAvailable() && slot.isSlotCompatible(vehicleType)) {
                answer++;
            }
        }
        return answer;
    }
}
