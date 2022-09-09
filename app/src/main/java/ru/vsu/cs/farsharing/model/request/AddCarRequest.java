package ru.vsu.cs.farsharing.model.request;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddCarRequest {
    String brand;
    String model;
    String color;
    String bodyType;
    String stateNumber;
    Float pricePerHour;
    Float mileage;
    Float xcoord;
    Float ycoord;
}
