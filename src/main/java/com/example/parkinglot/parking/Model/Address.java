package com.example.parkinglot.parking.Model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {

    String street;
    String block;
    String city;
    String state;
    String country;
}
