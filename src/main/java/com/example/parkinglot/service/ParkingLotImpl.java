package com.example.parkinglot.service;

import com.example.parkinglot.model.Address;
import com.example.parkinglot.model.Enum.ParkingSlotType;
import com.example.parkinglot.model.Ticket;
import com.example.parkinglot.model.Vehicle;
import lombok.Data;

import java.util.List;
import java.util.Map;


@Data
public class ParkingLotImpl {
    private String nameOfParkingLot;
    private Address address;
    private List<ParkingFloorImpl> parkingFloorImpls;
    private static ParkingLotImpl parkingLotImpl =null;

    public ParkingLotImpl(String nameOfParkingLot, Address address, List<ParkingFloorImpl> parkingFloorImpls) {
        this.nameOfParkingLot = nameOfParkingLot;
        this.address = address;
        this.parkingFloorImpls = parkingFloorImpls;
    }

    public static ParkingLotImpl getInstance(String nameOfParkingLot, Address address,
                                             List<ParkingFloorImpl> parkingFloorImpls){
        if(parkingLotImpl ==null){
            parkingLotImpl = new ParkingLotImpl(nameOfParkingLot,address, parkingFloorImpls);
        }
        return parkingLotImpl;
    }

    public void addFloors(String name,Map<ParkingSlotType, List<ParkingSlotImpl>> parkSlots){
       ParkingFloorImpl parkingFloorImpl = new ParkingFloorImpl(name,parkSlots);
       parkingFloorImpl.add(parkingFloorImpl);
    }

    public void removeFloors(ParkingFloorImpl parkingFloorImpl){
        parkingFloorImpls.remove(parkingFloorImpl);
    }

    public Ticket assignTicket(Vehicle vehicle){
        //to assign ticket we need parking slot for the vehicle
       ParkingSlotImpl parkingSlotImpl = getParkingSlotForVehicleAndPark(vehicle);
       if(parkingSlotImpl ==null) return null;
       Ticket parkingTicket = createTicketForSlot(parkingSlotImpl,vehicle);
       //persist ticket to database
        return parkingTicket;
    }

    public double pay(Ticket ticket){
        long endTime = System.currentTimeMillis();
        ticket.getParkingSlotImpl().removeVehicle();
        int duration = (int)(endTime- ticket.getStartTime())/1000;
        double price =ticket.getParkingSlotImpl().getParkingSlotType().getPriceForParking(duration);
        //persist record to database;
        return price;
    }

    private Ticket createTicketForSlot(ParkingSlotImpl parkingSlotImpl, Vehicle vehicle){
        return Ticket.createTicket(vehicle, parkingSlotImpl);
    }

    private ParkingSlotImpl getParkingSlotForVehicleAndPark(Vehicle vehicle){
        ParkingSlotImpl parkingSlotImpl = null;
        for(ParkingFloorImpl floor : parkingFloorImpls){
           parkingSlotImpl =floor.getRelevantSlotForVehicleAndPark(vehicle);
           if(parkingSlotImpl !=null) break;
        }
        return parkingSlotImpl;
    }
}
