package requests;
import app.ParkingLotManager;

import java.util.Scanner;
import utils.ScannerSingleton;

public class CreateParkingLotRequest implements Request {
    ParkingLotManager parkingLotManager = ParkingLotManager.getInstance();

    public void handleRequest() {
        Scanner sc = ScannerSingleton.getInstance();
        int parkingLotId = sc.nextInt();
        int numberOfFloors = sc.nextInt();
        int numberOfSlotsPerFloor = sc.nextInt();
        String response = parkingLotManager.createParkingLot(parkingLotId, numberOfFloors, numberOfSlotsPerFloor);
        System.out.print(response);
    }
}
