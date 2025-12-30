package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ParkingLot {
    int numberOfFloors, numberOfSlotsPerFloor, parkingLotId;
    List<Floor> floors;

    public ParkingLot(int parkingLotId, int numberOfFloors, int numberOfSlotsPerFloor) {
        this.parkingLotId = parkingLotId;
        this.numberOfFloors = numberOfFloors;
        this.numberOfSlotsPerFloor = numberOfSlotsPerFloor;

        floors = new ArrayList<>(numberOfFloors);
        for(int i = 0; i < numberOfFloors; i++) {
            floors.add(i, new Floor(i, this));
        }
    }

    String parkVehicle(Vehicle vehicleToBeParked) {
        for(int i = 0; i < numberOfFloors; i++) {
            Optional<Slot> optionalSlot = floors.get(i).fetchAvailableSlot(vehicleToBeParked.vehicleType);
            if(optionalSlot.isPresent()) {
                Slot slot = optionalSlot.get();
                slot.park(vehicleToBeParked);
                slot.vehicle = vehicleToBeParked;
                String ticketId = generateTicketId(parkingLotId, slot.floor.floorNumber, slot.slotNumber);
                return String.format("Parked vehicle. Ticket ID : %s\n", ticketId);
            }
        }
        return "Parking Lot Full\n";
    }

    String unparkVehicle(int floorNumber, int slotNumber) {
        if (floorNumber < 0 || floorNumber >= floors.size()) {
            return "Invalid Ticket\n";
        }
    
        Floor floor = floors.get(floorNumber);
        if (floor == null || slotNumber < 0 || slotNumber >= floor.slots.size()) {
            return "Invalid Ticket\n";
        }
    
        Slot slot = floor.slots.get(slotNumber);
        if (slot == null || slot.isSlotAvailable()) {
            return "Invalid Ticket\n";
        }

        Vehicle vehicleToBeUnparked = slot.vehicle;
        slot.unpark();
        return String.format("Unparked vehicle with Registration Number : %s and Color : %s\n", vehicleToBeUnparked.registrationNumber, vehicleToBeUnparked.color);
    }

    String displayFreeCount(VehicleType vehicleType) {
        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < numberOfFloors; i++) {
            int numberOfFreeSlots = floors.get(i).fetchNumberOfFreeSlots(vehicleType);
            answer.append("Number of free slots for ")
                .append(vehicleType.name().toLowerCase())
                .append(" on Floor ")
                .append(i)
                .append(" : ")
                .append(numberOfFreeSlots)
                .append("\n");
        }
        
        return answer.toString();
    }

    String displayFreeSlots(VehicleType vehicleType) {
        return displaySlots(vehicleType, Slot::isSlotAvailable, "Free");
    }

    String displayOccupiedSlots(VehicleType vehicleType) {
        return displaySlots(vehicleType, slot -> !slot.isSlotAvailable(), "Occupied");
    }

    private String displaySlots(VehicleType vehicleType, Predicate<Slot> slotCondition, String slotType) {
        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < numberOfFloors; i++) {
            List<Integer> matchingSlots = floors.get(i).fetchSlotsMatching(vehicleType, slotCondition);

            if (matchingSlots.isEmpty()) continue;

            String slotsString = matchingSlots.stream()
                                            .map(String::valueOf)
                                            .collect(Collectors.joining(","));
            
            answer.append(slotType)
                .append(" slots for ")
                .append(vehicleType.name().toLowerCase())
                .append(" on Floor ")
                .append(i)
                .append(" : ")
                .append(slotsString)
                .append("\n");
        }

        return answer.toString();
    }

    private String generateTicketId(int parkingLotId, int floorNumber, int slotNumber) {
        return parkingLotId + "_" + floorNumber + "_" + slotNumber;
    }
}
