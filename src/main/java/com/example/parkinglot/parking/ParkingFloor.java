package com.example.parkinglot.parking;

import com.example.parkinglot.parking.model.Enum.ParkingSlotType;
import com.example.parkinglot.parking.model.Vehicle;
import com.example.parkinglot.parking.model.Enum.VehicleCategory;

import java.util.Map;

public class ParkingFloor {
    String name;
    Map<ParkingSlotType, Map<String,ParkingSlot>> parkingSlots;
    public ParkingFloor(String name, Map<ParkingSlotType, Map<String, ParkingSlot>> parkingSlots) {
        this.name = name;
        this.parkingSlots = parkingSlots;
    }

    public ParkingSlot getRelevantSlotForVehicleAndPark(Vehicle vehicle){
        VehicleCategory vehicleCategory=vehicle.getVehicleCategory();
        ParkingSlotType parkingSlotType =pickCorrectSlot(vehicleCategory);
        Map<String,ParkingSlot> relevantParkingSlot=parkingSlots.get(parkingSlotType);
        ParkingSlot slot= null;
         for(Map.Entry<String,ParkingSlot> m:relevantParkingSlot.entrySet()){
             if(m.getValue().isAvailable){
                 slot= m.getValue();
                 slot.addVehicle(vehicle);
                 break;
             }
         }
       return slot;
    }

    public ParkingSlotType pickCorrectSlot(VehicleCategory vehicleCategory){
        if(vehicleCategory.equals(VehicleCategory.Motorcycle))
            return ParkingSlotType.TwoWheeler;
       else if(vehicleCategory.equals(VehicleCategory.Sedan))
           return ParkingSlotType.Medium;
       else if(vehicleCategory.equals(VehicleCategory.Truck))
           return ParkingSlotType.Large;
       return null;
    }


    public void add(ParkingFloor parkingFloor) {

    }
}
