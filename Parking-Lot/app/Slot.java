package app;
public class Slot {
    int slotNumber;
    Floor floor;
    Vehicle vehicle;
    
    Slot(int slotNumber, Floor floor) {
        this.slotNumber = slotNumber;
        this.floor = floor;
    }

    void park(Vehicle vehicleToBeParked) {
        vehicle = vehicleToBeParked;
    }

    void unpark() {
        vehicle = null;
    }

    boolean isSlotAvailable() {
        return vehicle == null;
    }

    boolean isSlotCompatible(VehicleType vehicleType) {
        if (slotNumber == 0) {
            return vehicleType == VehicleType.TRUCK;
        } else if (slotNumber <= 2) {
            return vehicleType == VehicleType.BIKE;
        } else {
            return vehicleType == VehicleType.CAR;
        }
    }
}
