package ru.vsu.cs.farsharing.model.request;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCarRequest {
    String brand;
    String model;
    String color;
    String bodyType;
    UUID location;
    String stateNumber;
    Float pricePerHour;
    Float mileage;
}
