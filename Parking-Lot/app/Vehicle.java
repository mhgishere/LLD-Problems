package app;
public class Vehicle {
    VehicleType vehicleType;
    String registrationNumber, color;

    public Vehicle(VehicleType vehicleType, String registrationNumber, String color) {
        this.vehicleType = vehicleType;
        this.registrationNumber = registrationNumber;
        this.color = color;
    }
}
