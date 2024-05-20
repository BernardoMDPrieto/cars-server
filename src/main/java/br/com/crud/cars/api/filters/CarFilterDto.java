package br.com.crud.cars.api.filters;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarFilterDto {
    private String model;
    private String color;
    private String make;
    private Integer year;
    private BigDecimal price;
    private Integer quantity;
}
