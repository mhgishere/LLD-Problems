package requests;

import java.util.Scanner;

import app.ParkingLotManager;
import app.VehicleType;
import utils.ScannerSingleton;

public class DisplayRequest implements Request {
    ParkingLotManager parkingLotManager = ParkingLotManager.getInstance();

    public void handleRequest() {
        Scanner sc = ScannerSingleton.getInstance();
        int parkingLotId = sc.nextInt();
        String displayType = sc.next();
        VehicleType vehicleType = VehicleType.fromString(sc.next());
        String response = parkingLotManager.display(parkingLotId, displayType, vehicleType);
        System.out.print(response);
    }
}
