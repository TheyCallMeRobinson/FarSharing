package ru.vsu.cs.farsharing.activity.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CarListItem {
    private String status;
    private String brand;
    private String model;
    private String stateNumber;
}
