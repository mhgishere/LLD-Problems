package app;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class ParkingLotManager {
    private static ParkingLotManager instance;

    Map<String, BiFunction<ParkingLot, VehicleType, String>> displayHandlers;
    Map<Integer, ParkingLot> parkingLots = new HashMap<>();

    private ParkingLotManager() {
        displayHandlers = new HashMap<>();
        displayHandlers.put("free_count", ParkingLot::displayFreeCount);
        displayHandlers.put("free_slots", ParkingLot::displayFreeSlots);
        displayHandlers.put("occupied_slots", ParkingLot::displayOccupiedSlots);
    }
    
    public static ParkingLotManager getInstance() {
        if (instance == null) {
            instance = new ParkingLotManager();
        }
        return instance;
    }

    public String createParkingLot(int parkingLotId, int numberOfFloors, int numberOfSlotsPerFloor) {
        if(parkingLots.containsKey(parkingLotId)) {
            return String.format("Parking Lot with ID = [%d] already exists\n", parkingLotId);
        }
        parkingLots.put(parkingLotId, new ParkingLot(parkingLotId, numberOfFloors, numberOfSlotsPerFloor));
        return String.format("Created parking lot with ID = [%d] consisting of %d floors and %d slots per floor\n", parkingLotId, numberOfFloors, numberOfSlotsPerFloor);
    }

    public String parkVehicle(int parkingLotId, Vehicle vehicle) {
        ParkingLot parkingLot = parkingLots.get(parkingLotId);
        if(parkingLot == null) {
            return String.format("Parking Lot with ID = [%d] doesn't exist\n", parkingLotId);
        }
        return parkingLot.parkVehicle(vehicle);
    }

    public String unparkVehicle(String ticketId) {
        String[] parts = ticketId.split("_");
        if (parts.length != 3) {
            return "Invalid Ticket\n";
        }
        int parkingLotId = Integer.parseInt(parts[0]);
        int floorNumber = Integer.parseInt(parts[1]);
        int slotNumber = Integer.parseInt(parts[2]);
        ParkingLot parkingLot = parkingLots.get(parkingLotId);
        if(parkingLot == null) {
            return String.format("Parking Lot with ID = [%d] doesn't exist\n", parkingLotId);
        }
        return parkingLot.unparkVehicle(floorNumber, slotNumber);
    }

    public String display(int parkingLotId, String displayType, VehicleType vehicleType) {
        ParkingLot parkingLot = parkingLots.get(parkingLotId);
        if(parkingLot == null) {
            return String.format("Parking Lot with ID = [%d] doesn't exist\n", parkingLotId);
        }
        BiFunction<ParkingLot, VehicleType, String> displayHandler = displayHandlers.get(displayType);
        return displayHandler.apply(parkingLot, vehicleType);
    }
}