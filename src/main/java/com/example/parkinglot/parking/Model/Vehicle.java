package com.example.parkinglot.parking.Model;


import com.example.parkinglot.parking.Model.Enum.VehicleCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Vehicle {
    String vehicleNumber;
    VehicleCategory vehicleCategory;
}
