package ru.vsu.cs.farsharing.model.request;

import java.util.UUID;

public class UpdateCarRequest {
    String brand;
    String model;
    String color;
    String bodyType;
    UUID location;
    Boolean isAvailable;
    Float mileage;
}
