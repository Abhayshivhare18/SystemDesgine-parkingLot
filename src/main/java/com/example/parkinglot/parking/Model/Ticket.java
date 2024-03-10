package com.example.parkinglot.parking.Model;

import com.example.parkinglot.parking.ParkingSlot;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ticket {

    String ticketNumber;
    long startTime;
    long endTime;
    Vehicle vehicle;
    ParkingSlot parkingSlot;

    public static Ticket createTicket(Vehicle vehicle, ParkingSlot parkingSlot){
        return Ticket.builder()
                .parkingSlot(parkingSlot)
                .startTime(System.currentTimeMillis())
                .vehicle(vehicle)
                .ticketNumber(vehicle.getVehicleNumber()+System.currentTimeMillis())
                .build();
    }
}
