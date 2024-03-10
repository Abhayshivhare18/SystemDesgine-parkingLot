package com.example.parkinglot;

import com.example.parkinglot.model.Address;
import com.example.parkinglot.model.Enum.ParkingSlotType;
import com.example.parkinglot.model.Enum.VehicleCategory;
import com.example.parkinglot.model.Ticket;
import com.example.parkinglot.model.Vehicle;
import com.example.parkinglot.service.ParkingFloorImpl;
import com.example.parkinglot.service.ParkingLotImpl;
import com.example.parkinglot.service.ParkingSlotImpl;
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
		Map<ParkingSlotType, List<ParkingSlotImpl>> allSlots = new HashMap<>();

		List<ParkingSlotImpl> smallSlot = new ArrayList<>();
		smallSlot.add(new ParkingSlotImpl("S1",ParkingSlotType.TwoWheeler));
		smallSlot.add(new ParkingSlotImpl("S2",ParkingSlotType.TwoWheeler));
		smallSlot.add(new ParkingSlotImpl("S3",ParkingSlotType.TwoWheeler));
		allSlots.put(ParkingSlotType.TwoWheeler,smallSlot);

		List<ParkingSlotImpl>  mediumSlot = new ArrayList<>();
		mediumSlot.add(new ParkingSlotImpl("m1",ParkingSlotType.Medium));
		mediumSlot.add(new ParkingSlotImpl("m2",ParkingSlotType.Medium));
		mediumSlot.add(new ParkingSlotImpl("m2",ParkingSlotType.Medium));
		allSlots.put(ParkingSlotType.Medium,mediumSlot);


		List<ParkingSlotImpl> largeSlot = new ArrayList<>();
		largeSlot.add(new ParkingSlotImpl("l1",ParkingSlotType.Large));
		largeSlot.add(new ParkingSlotImpl("l2",ParkingSlotType.Large));
		largeSlot.add(new ParkingSlotImpl("l3",ParkingSlotType.Large));
        allSlots.put(ParkingSlotType.Large,largeSlot);

		ParkingFloorImpl parkingFloorImplOne = new ParkingFloorImpl("1",allSlots);
		ParkingFloorImpl parkingFloorImplTwo = new ParkingFloorImpl("2",allSlots);
		ParkingFloorImpl parkingFloorImplThree = new ParkingFloorImpl("3",allSlots);
		ParkingFloorImpl parkingFloorImplFour = new ParkingFloorImpl("4",allSlots);

		List<ParkingFloorImpl> parkingFloorImpls = new ArrayList<>();
		parkingFloorImpls.add(parkingFloorImplOne);
		parkingFloorImpls.add(parkingFloorImplTwo);
		parkingFloorImpls.add(parkingFloorImplThree);
		parkingFloorImpls.add(parkingFloorImplFour);

		ParkingLotImpl parkingLotImpl = ParkingLotImpl.getInstance(nameOfParkingLot,address, parkingFloorImpls);

		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleCategory(VehicleCategory.Truck);
		vehicle.setVehicleNumber("MP-01-MA-1001");

		Ticket ticket = parkingLotImpl.assignTicket(vehicle);
		System.out.println("Ticket number >>" + ticket.getTicketNumber());
		//persist the ticket to db here
		Thread.sleep(1000);
		double price = parkingLotImpl.pay(ticket);
		System.out.println("price is >>"+ price);







		SpringApplication.run(ParkingLotManagementSystemApplication.class, args);
	}


}
