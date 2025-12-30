package requests;

import java.util.Scanner;

import app.ParkingLotManager;
import utils.ScannerSingleton;

public class UnparkVehicleRequest implements Request {
    ParkingLotManager parkingLotManager = ParkingLotManager.getInstance();

    public void handleRequest() {
        Scanner sc = ScannerSingleton.getInstance();
        String ticketId = sc.next();
        String response = parkingLotManager.unparkVehicle(ticketId);
        System.out.print(response);
    }
}
