package br.com.crud.cars.repository;

import br.com.crud.cars.model.CarModel;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface CarRepository extends JpaRepository<CarModel, UUID>, JpaSpecificationExecutor<CarModel> {

//    @Override
//    <S extends CarModel> boolean exists(Example<S> example);



}

