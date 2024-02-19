package com.cars.carapi.service;
import com.cars.carapi.model.Car;
import com.cars.carapi.api.response.CarDTO;
import com.cars.carapi.model.CarPageDTO;
import com.cars.carapi.utils.CarMapper;
import com.cars.carapi.repository.ICarRepository;

import com.cars.carapi.utils.exceptions.NotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public class CarService {

    @Autowired
    private ICarRepository carRepository;

    @Autowired
    private CarMapper carMapper;

    public CarPageDTO listFilteredCars(String brandPattern, String modelPattern, String yearPattern, String colorPattern, int pageNumber, int size) {
        Specification<Car> filter = Specification.where(null);

        if (brandPattern != null && !brandPattern.isEmpty()) {
            filter = filter.and(ICarRepository.Specs.byBrandCar(brandPattern));
        }
        if (modelPattern != null && !modelPattern.isEmpty()) {
            filter = filter.and(ICarRepository.Specs.byModelCar(modelPattern));
        }
        if (yearPattern != null && !yearPattern.isEmpty()) {
            filter = filter.and(ICarRepository.Specs.byYearCar(yearPattern));
        }
        if (colorPattern != null && !colorPattern.isEmpty()) {
            filter = filter.and(ICarRepository.Specs.byColorCar(colorPattern));
        }

        Page<Car> page = carRepository.findAll(filter, PageRequest.of(pageNumber, size));

        List<CarDTO> cars = page.map(car -> carMapper.toDTO(car)).toList();
        return new CarPageDTO(cars, page.getTotalElements(), page.getTotalPages());
    }


    public CarPageDTO listAll(int pageNumber, int size) {
        //GET paginado
        Page<Car> page = carRepository.findAll(PageRequest.of(pageNumber, size));

        //pega os registros, mapeia, transforma em DTO e retorna em lista
        List<CarDTO> cars = page.get().map(car -> carMapper.toDTO(car)).toList();
        return new CarPageDTO(cars, page.getTotalElements(), page.getTotalPages());
    }

    public CarDTO createCar(@Valid @NotNull CarDTO car) {
        return carMapper.toDTO(carRepository.save(carMapper.toEntity(car)));
    }

    public CarDTO updateCar(@NotNull @Positive Long id, @Valid CarDTO car) {
        return carRepository.findById(id).map(item ->{
            item.setBrandCar(car.getBrandCar());
            item.setModelCar(car.getModelCar());
            item.setYearCar(car.getYearCar());
            item.setColorCar(car.getColorCar());
            return carMapper.toDTO(carRepository.save(item));
        }).orElseThrow(() -> new NotFoundException(id));
    }

    public void deleteCar( @NotNull @Positive Long id) {

        carRepository.delete(carRepository.findById(id).orElseThrow(() -> new NotFoundException(id)));
    }

    public CarDTO listById(@NotNull @Positive Long id) {
        return carRepository.findById(id).map(carMapper::toDTO)
                .orElseThrow(() -> new NotFoundException(id));
    }

}
