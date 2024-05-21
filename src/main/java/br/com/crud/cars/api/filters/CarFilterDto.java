package br.com.crud.cars.api.filters;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CarFilterDto {
    private String model;
    private String color;
    private String make;
    private Integer year;
    private BigDecimal price;
    private Integer quantity;
}
