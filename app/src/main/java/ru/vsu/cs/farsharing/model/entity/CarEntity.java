package ru.vsu.cs.farsharing.model.entity;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
