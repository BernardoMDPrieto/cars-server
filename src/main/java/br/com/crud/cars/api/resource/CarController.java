package br.com.crud.cars.api.resource;

import br.com.crud.cars.api.request.CarRequestDTO;
import br.com.crud.cars.api.response.CarResponseDTO;
import br.com.crud.cars.service.CarService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
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
    public ResponseEntity<List<CarResponseDTO>> getCars() {
            List<CarResponseDTO> cars = carService.getAllCars();
            return ResponseEntity.status(HttpStatus.OK).body(cars);
    }
    @GetMapping("/cars/specifications")
    public ResponseEntity<List<CarResponseDTO>> getCarsBySpecification(@RequestParam(required = false) String model,
                                                                       @RequestParam(required = false) String color,
                                                                       @RequestParam(required = false) String make,
                                                                       @RequestParam(required = false) int year,
                                                                       @RequestParam(required = false) BigDecimal price,
                                                                       @RequestParam(required = false) int quantity){


        List<CarResponseDTO> cars = carService.getCarsBySpecification(model,color,make,year,price,quantity);
        return ResponseEntity.status(HttpStatus.OK).body(cars);

    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<CarResponseDTO> getCar(@PathVariable(value = "id") UUID id){
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
                                                         @Valid @RequestParam("quantity") @Min(0) Integer newQuantity){
        CarResponseDTO updatedQuantity = carService.updateQuantity(id,newQuantity);
        return ResponseEntity.status(HttpStatus.OK).body(updatedQuantity);
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<CarResponseDTO> deleteCar(@PathVariable(value = "id") UUID id){
            CarResponseDTO deletedCar = carService.deleteCar(id);
            return ResponseEntity.status(HttpStatus.OK).body(deletedCar);
    }
}