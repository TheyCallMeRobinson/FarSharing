package ru.vsu.cs.farsharing.model.request;

import java.util.UUID;

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
