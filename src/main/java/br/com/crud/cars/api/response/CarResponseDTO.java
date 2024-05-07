package br.com.crud.cars.api.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class CarResponseDTO {
    private UUID id;
    private String make;
    private String model;
    private String color;
    private int year;
    private BigDecimal price;
}
