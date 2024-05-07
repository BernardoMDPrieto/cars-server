package br.com.crud.cars.api.resource;

import br.com.crud.cars.api.request.CarRequestDTO;
import br.com.crud.cars.api.response.CarResponseDTO;
import br.com.crud.cars.service.CarService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping("/cars")
    public ResponseEntity<CarResponseDTO> saveCar(@RequestBody @Valid CarRequestDTO carRequestDTO) {
        try {
            CarResponseDTO savedCar = carService.saveCar(carRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarResponseDTO>> getCars() {
        try {
            List<CarResponseDTO> cars = carService.getAllCars();
            return ResponseEntity.status(HttpStatus.OK).body(cars);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<CarResponseDTO> getCar(@PathVariable(value = "id") UUID id){
        try {
            CarResponseDTO car = carService.getOneCar(id);
            return ResponseEntity.status(HttpStatus.OK).body(car);
        }catch (ValidationException e) {
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/cars/{id}")
    public ResponseEntity<CarResponseDTO> updateCar(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid CarRequestDTO carRequestDTO) {
        try {
            CarResponseDTO updatedCar = carService.updateCar(id, carRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(updatedCar);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<CarResponseDTO> deleteCar(@PathVariable(value = "id") UUID id){
        try {
            CarResponseDTO deletedCar = carService.deleteCar(id);
            return ResponseEntity.status(HttpStatus.OK).body(deletedCar);
        } catch (ValidationException e){
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}