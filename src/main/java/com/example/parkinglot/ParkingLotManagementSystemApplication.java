package com.example.parkinglot;

import com.example.parkinglot.parking.model.Address;
import com.example.parkinglot.parking.model.Enum.ParkingSlotType;
import com.example.parkinglot.parking.model.Enum.VehicleCategory;
import com.example.parkinglot.parking.model.Ticket;
import com.example.parkinglot.parking.model.Vehicle;
import com.example.parkinglot.parking.ParkingFloor;
import com.example.parkinglot.parking.ParkingLot;
import com.example.parkinglot.parking.ParkingSlot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ParkingLotManagementSystemApplication {

	public static void main(String[] args) throws InterruptedException {
		String nameOfParkingLot ="Abhay Parking Lot";
		Address address = Address.builder().city("Bhopal").country("India").
				state("MP").build();
		Map<ParkingSlotType, Map<String,ParkingSlot>> allSlots = new HashMap<>();

		Map<String,ParkingSlot> smallSlot = new HashMap<>();
		smallSlot.put("S1",new ParkingSlot("S1",ParkingSlotType.TwoWheeler));
		smallSlot.put("S2",new ParkingSlot("S2",ParkingSlotType.TwoWheeler));
		smallSlot.put("S3",new ParkingSlot("S3",ParkingSlotType.TwoWheeler));
		allSlots.put(ParkingSlotType.TwoWheeler,smallSlot);

		Map<String,ParkingSlot>  mediumSlot = new HashMap<>();
		mediumSlot.put("m1",new ParkingSlot("m1",ParkingSlotType.Medium));
		mediumSlot.put("m2",new ParkingSlot("m2",ParkingSlotType.Medium));
		mediumSlot.put("m2",new ParkingSlot("m2",ParkingSlotType.Medium));
		allSlots.put(ParkingSlotType.Medium,mediumSlot);


		Map<String,ParkingSlot> largeSlot = new HashMap<>();
		largeSlot.put("l1",new ParkingSlot("l1",ParkingSlotType.Large));
		largeSlot.put("l2",new ParkingSlot("l2",ParkingSlotType.Large));
		largeSlot.put("l3",new ParkingSlot("l3",ParkingSlotType.Large));
        allSlots.put(ParkingSlotType.Large,largeSlot);

		ParkingFloor parkingFloorOne = new ParkingFloor("1",allSlots);
		ParkingFloor parkingFloorTwo = new ParkingFloor("2",allSlots);
		ParkingFloor parkingFloorThree = new ParkingFloor("3",allSlots);
		ParkingFloor parkingFloorFour = new ParkingFloor("4",allSlots);

		List<ParkingFloor> parkingFloors = new ArrayList<>();
		parkingFloors.add(parkingFloorOne);
		parkingFloors.add(parkingFloorTwo);
		parkingFloors.add(parkingFloorThree);
		parkingFloors.add(parkingFloorFour);

		ParkingLot parkingLot = ParkingLot.getInstance(nameOfParkingLot,address,parkingFloors);

		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleCategory(VehicleCategory.Truck);
		vehicle.setVehicleNumber("MP-01-MA-1001");

		Ticket ticket = parkingLot.assignTicket(vehicle);
		System.out.println("Ticket number >>" + ticket.getTicketNumber());
		//persist the ticket to db here
		Thread.sleep(1000);
		double price = parkingLot.pay(ticket);
		System.out.println("price is >>"+ price);







		SpringApplication.run(ParkingLotManagementSystemApplication.class, args);
	}


}
