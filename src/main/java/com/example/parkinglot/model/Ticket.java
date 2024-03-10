package com.example.parkinglot.model;

import com.example.parkinglot.service.ParkingSlotImpl;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ticket {

    String ticketNumber;
    long startTime;
    long endTime;
    Vehicle vehicle;
    ParkingSlotImpl parkingSlotImpl;

    public static Ticket createTicket(Vehicle vehicle, ParkingSlotImpl parkingSlotImpl){
        return Ticket.builder()
                .parkingSlotImpl(parkingSlotImpl)
                .startTime(System.currentTimeMillis())
                .vehicle(vehicle)
                .ticketNumber(vehicle.getVehicleNumber()+System.currentTimeMillis())
                .build();
    }
}
