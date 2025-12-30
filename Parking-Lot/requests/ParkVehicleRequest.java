package requests;

import java.util.Scanner;

import app.ParkingLotManager;
import app.Vehicle;
import app.VehicleType;
import utils.ScannerSingleton;

public class ParkVehicleRequest implements Request {
    ParkingLotManager parkingLotManager = ParkingLotManager.getInstance();

    public void handleRequest() {
        Scanner sc = ScannerSingleton.getInstance();
        int parkingLotId = sc.nextInt();
        VehicleType vehicleType = VehicleType.fromString(sc.next());
        String registrationNumber = sc.next();
        String color = sc.next();
        String response = parkingLotManager.parkVehicle(parkingLotId, new Vehicle(vehicleType, registrationNumber, color));
        System.out.print(response);
    }
}
