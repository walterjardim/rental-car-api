package br.org.rentalcarapi.infra.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CarDTO {
  
    @NotNull(message = "Manufacture year is required")
    @Positive(message = "Manufacture year must be greater than zero")
    int manufactureYear;

    @NotBlank(message = "License plate is required")
    String licensePlate;

    @NotBlank(message = "Model is required")
    String model;

    @NotBlank(message = "Color is required")
    String color;
}
