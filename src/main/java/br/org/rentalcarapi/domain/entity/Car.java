package br.org.rentalcarapi.domain.entity;

public class Car {

    private Long id;
    private int manufactureYear;
    private String licensePlate;
    private String model; 
    private String color;
    
    public Car() {}

    public Car(int manufactureYear, String licensePlate, String model, String color) {
        this.manufactureYear = manufactureYear;
        this.licensePlate = licensePlate;
        this.model = model;
        this.color = color;
    }
    public Long getId() {
        return id;
    }
    
    public String getLicensePlate() {
        return licensePlate;
    }
    public String getModel() {
        return model;
    }
    public String getColor() {
        return color;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

}
