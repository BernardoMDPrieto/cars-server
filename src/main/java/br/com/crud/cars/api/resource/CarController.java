package br.com.crud.cars.api.resource;

import br.com.crud.cars.api.filters.CarFilterDto;
import br.com.crud.cars.api.request.CarRequestDTO;
import br.com.crud.cars.api.response.CarResponseDTO;
import br.com.crud.cars.service.CarService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping("/cars")
    public ResponseEntity<CarResponseDTO> saveCar(@RequestBody @Valid CarRequestDTO carRequestDTO) {
        CarResponseDTO savedCar = carService.saveCar(carRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
    }

    @GetMapping("/cars")
    public ResponseEntity<Page<CarResponseDTO>> getCars(
            @PageableDefault(page = 0, size = 10, sort = "make", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<CarResponseDTO> cars = carService.getAllCars(pageable.getPageNumber(),pageable.getPageSize());
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @GetMapping("/cars/spec")
    public ResponseEntity<Page<CarResponseDTO>> getCarsBySpecification(@ModelAttribute CarFilterDto carFilterDto,
                                                                       @PageableDefault(page = 0, size = 10, sort = "make", direction = Sort.Direction.ASC) Pageable pageable) {


        Page<CarResponseDTO> cars = carService.getCarsBySpecification(carFilterDto, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(cars);

    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<CarResponseDTO> getCar(@PathVariable(value = "id") UUID id) {
        CarResponseDTO car = carService.getOneCar(id);
        return ResponseEntity.status(HttpStatus.OK).body(car);
    }


    @PutMapping("/cars/{id}")
    public ResponseEntity<CarResponseDTO> updateCar(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid CarRequestDTO carRequestDTO) {
        CarResponseDTO updatedCar = carService.updateCar(id, carRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedCar);
    }

    @PutMapping("/cars/{id}/quantity")
    public ResponseEntity<CarResponseDTO> updateQuantity(@PathVariable(value = "id") UUID id,
                                                         @Valid @RequestParam("quantity") @Min(0) Integer newQuantity) {
        CarResponseDTO updatedQuantity = carService.updateQuantity(id, newQuantity);
        return ResponseEntity.status(HttpStatus.OK).body(updatedQuantity);
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<CarResponseDTO> deleteCar(@PathVariable(value = "id") UUID id) {
        CarResponseDTO deletedCar = carService.deleteCar(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedCar);
    }
}