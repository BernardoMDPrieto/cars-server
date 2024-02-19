package com.cars.carapi.api.resource;

import com.cars.carapi.api.response.CarDTO;
import com.cars.carapi.model.CarPageDTO;
import com.cars.carapi.service.CarService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated //Executa as validação dos parâmetros
@RestController
@RequestMapping("/api/cars")
@Slf4j
public class CarController {

    @Autowired
    private CarService carService;

    //Get paginado e com opção de filtro

    @CrossOrigin //Resolve problema de Cors
    @GetMapping
    public CarPageDTO listAll(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") @Max(100) int size,
            @RequestParam(required = false) String brandCar,
            @RequestParam(required = false) String modelCar,
            @RequestParam(required = false) String yearCar,
            @RequestParam(required = false) String colorCar
    ) {
        if (brandCar != null || modelCar != null || yearCar != null || colorCar != null) {
            log.info("Obtendo registros filtrados no banco.");
            return carService.listFilteredCars(brandCar, modelCar, yearCar, colorCar, pageNumber, size);
        } else {
            log.info("Obtendo todos os registros no banco.");
            return carService.listAll(pageNumber, size);
        }
    }

    @CrossOrigin
    @PostMapping
    public CarDTO createCar(@RequestBody @Valid @NotNull CarDTO car) {

        log.info("Registrando dados no banco. Marca= {}, Modelo= {}, Ano= {}, Cor= {}", car.getBrandCar(), car.getModelCar(), car.getYearCar(), car.getColorCar());
        return carService.createCar(car);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public CarDTO updateCar(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid CarDTO car) {

        log.info("Atualizando dados no banco do registro de id {}.", id);
        return carService.updateCar(id, car);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable @NotNull @Positive Long id) {
        log.info("Deletando dados no banco do registro de id {}.", id);
        carService.deleteCar(id);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public CarDTO listById(@PathVariable @NotNull @Positive Long id) {
        log.info("Obtendo dado de id " + id + " no banco.");
        return carService.listById(id);
    }

//    @CrossOrigin
//    @GetMapping("/filter")
//    public CarPageDTO listFilterCars(
//            @RequestParam(required = false) String brandCar,
//            @RequestParam(required = false) String modelCar,
//            @RequestParam(required = false) String yearCar,
//            @RequestParam(required = false) String colorCar,
//            @RequestParam(defaultValue = "0") int pageNumber,
//            @RequestParam(defaultValue = "10") @Max(100) int size
//    ) {
//        log.info("Obtendo registros filtrados no banco.");
//        return carService.listFilteredCars(brandCar, modelCar, yearCar, colorCar, pageNumber, size);
//    }

}
