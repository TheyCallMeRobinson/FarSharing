package ru.vsu.cs.farsharing.model.response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BriefCarInfoResponse {
    String stateNumber;
    String brand;
    String model;
    Boolean isAvailable;
}
