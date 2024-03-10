package com.example.parkinglot.service;

import com.example.parkinglot.model.Enum.ParkingSlotType;
import com.example.parkinglot.model.Vehicle;
import lombok.Builder;
import lombok.Data;

@Data
public class ParkingSlotImpl {
    String name;
    @Builder.Default
    boolean isAvailable =true;
    Vehicle vehicle;
    ParkingSlotType parkingSlotType;

    public ParkingSlotImpl(String name, ParkingSlotType parkingSlotType) {
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
