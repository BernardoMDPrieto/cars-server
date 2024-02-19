package com.cars.carapi.model;

import com.cars.carapi.api.response.CarDTO;

import java.util.List;

public record CarPageDTO(List<CarDTO> cars, long elements, int pages ) {
}
