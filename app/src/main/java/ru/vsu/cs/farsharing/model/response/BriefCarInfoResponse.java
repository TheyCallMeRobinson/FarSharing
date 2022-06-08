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

    public String getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(String stateNumber) {
        this.stateNumber = stateNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
