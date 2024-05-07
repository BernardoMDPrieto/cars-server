package br.com.crud.cars.repository;

import br.com.crud.cars.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface CarRepository extends JpaRepository<CarModel, UUID> {
    //Optional<CarModel> carExist(String model, String make, String color, int year, BigDecimal price);
}

