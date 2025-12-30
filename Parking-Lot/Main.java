import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import requests.CreateParkingLotRequest;
import requests.DisplayRequest;
import requests.ParkVehicleRequest;
import requests.Request;
import requests.UnparkVehicleRequest;
import utils.ScannerSingleton;

public class Main {
    public static void main(String[] args) {
        Map<String, Request> requestMapping = new HashMap<>();
        requestMapping.put("create_parking_lot", new CreateParkingLotRequest());
        requestMapping.put("park_vehicle", new ParkVehicleRequest());
        requestMapping.put("unpark_vehicle", new UnparkVehicleRequest());
        requestMapping.put("display", new DisplayRequest());

        Scanner sc = ScannerSingleton.getInstance();
        while(true) {
            String commandType = sc.next();
            if(commandType.equals("exit")) {
                break;
            }

            Request request = requestMapping.get(commandType);
            request.handleRequest();
        }
        
    }
}
