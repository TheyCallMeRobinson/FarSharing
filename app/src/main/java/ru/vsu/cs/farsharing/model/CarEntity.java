package ru.vsu.cs.farsharing.model;

import java.util.UUID;

public class CarEntity {
    UUID uid;
    String brand;
    String model;
    ColorEntity color;
    BodyTypeEntity bodyType;
    LocationEntity location;
    String stateNumber;
    Boolean isAvailable;
    Float pricePerHour;
    Float mileage;
}
