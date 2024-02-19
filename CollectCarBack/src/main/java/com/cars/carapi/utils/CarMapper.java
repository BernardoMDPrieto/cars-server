package com.cars.carapi.utils;

import com.cars.carapi.model.Car;
import com.cars.carapi.api.response.CarDTO;
import org.springframework.stereotype.Component;

//Provavelmente vai no utils

//Serviço que atua na conversão de Entidade para DTO e vice-versa
@Component
public class CarMapper {

    public CarDTO toDTO(Car car){

        if(car == null){
            return null;
        }

        return new CarDTO(car.getIdCar(), car.getBrandCar(), car.getModelCar(), car.getYearCar(), car.getColorCar());
    }

    public Car toEntity(CarDTO carDTO){

        if(carDTO == null){
            return null;
        }

        Car car = new Car();
        car.setIdCar(carDTO.getIdCar());
        car.setBrandCar(carDTO.getBrandCar());
        car.setModelCar(carDTO.getModelCar());
        car.setYearCar(carDTO.getYearCar());
        car.setColorCar(carDTO.getColorCar());
        return car;
    }


}
