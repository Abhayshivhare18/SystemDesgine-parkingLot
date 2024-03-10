package com.example.parkinglot.parking;

import com.example.parkinglot.parking.Model.Enum.ParkingSlotType;
import com.example.parkinglot.parking.Model.Vehicle;
import lombok.Builder;
import lombok.Data;

@Data
public class ParkingSlot {
    String name;
    @Builder.Default
    boolean isAvailable =true;
    Vehicle vehicle;
    ParkingSlotType parkingSlotType;

    public ParkingSlot(String name, ParkingSlotType parkingSlotType) {
        this.name = name;
        this.parkingSlotType = parkingSlotType;
    }
    protected  void addVehicle(Vehicle vehicle){
           this.vehicle = vehicle;
           this.isAvailable=false;
    }
    protected void removeVehicle(){
        this.vehicle=null;
        this.isAvailable=true;
    }
}
