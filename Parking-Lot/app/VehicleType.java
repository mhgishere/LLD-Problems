package app;
public enum VehicleType {
    TRUCK, BIKE, CAR;

    public static VehicleType fromString(String value) {
        return VehicleType.valueOf(value.trim().toUpperCase());
    }
}
