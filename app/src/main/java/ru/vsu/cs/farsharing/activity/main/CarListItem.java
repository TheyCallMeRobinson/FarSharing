package ru.vsu.cs.farsharing.activity.main;

public class CarListItem {
    private String status;
    private String brand;
    private String model;
    private String stateNumber;

    public CarListItem(String status, String brand, String model, String stateNumber) {
        this.status = status;
        this.brand = brand;
        this.model = model;
        this.stateNumber = stateNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(String stateNumber) {
        this.stateNumber = stateNumber;
    }
}
