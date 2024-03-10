package com.example.parkinglot.service;

import com.example.parkinglot.model.Enum.ParkingSlotType;
import com.example.parkinglot.model.Vehicle;
import com.example.parkinglot.model.Enum.VehicleCategory;

import java.util.List;
import java.util.Map;

public class ParkingFloorImpl {
    String name;
    Map<ParkingSlotType, List<ParkingSlotImpl>> parkingSlots;
    public ParkingFloorImpl(String name, Map<ParkingSlotType, List<ParkingSlotImpl>> parkingSlots) {
        this.name = name;
        this.parkingSlots = parkingSlots;
    }

    public ParkingSlotImpl getRelevantSlotForVehicleAndPark(Vehicle vehicle){
        VehicleCategory vehicleCategory=vehicle.getVehicleCategory();
        ParkingSlotType parkingSlotType =pickCorrectSlot(vehicleCategory);
        List<ParkingSlotImpl> relevantParkingSlotImpl =parkingSlots.get(parkingSlotType);
        ParkingSlotImpl slot= null;
         for(ParkingSlotImpl parkingSlotImpl : relevantParkingSlotImpl){
             if(parkingSlotImpl.isAvailable){
                 slot= parkingSlotImpl;
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


    public void add(ParkingFloorImpl parkingFloorImpl) {

    }
}
