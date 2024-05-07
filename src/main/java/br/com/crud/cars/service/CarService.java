package br.com.crud.cars.service;

import br.com.crud.cars.api.request.CarRequestDTO;
import br.com.crud.cars.api.response.CarResponseDTO;
import br.com.crud.cars.api.response.ErrorResponseDTO;
import br.com.crud.cars.model.CarModel;
import br.com.crud.cars.repository.CarRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;



    public List<CarResponseDTO> getAllCars() {
        List<CarModel> cars = carRepository.findAll();
        return cars.stream()
                .map(this::convertToCarResponse)
                .collect(Collectors.toList());
    }


    public CarResponseDTO getOneCar(UUID id){
        CarModel existingCar = carRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Não existe carro com esse ID: " + id));
        return convertToCarResponse(existingCar);
    }


    public CarResponseDTO saveCar(CarRequestDTO carRequestDTO){
        /*if (carExists(carRequestDTO)) {
            throw new ErrorResponseDTO("Esse carro já está cadastrado");
        }*/
        CarModel carModel = convertToCarModel(carRequestDTO);
        CarModel savedCar = carRepository.save(carModel);
        return convertToCarResponse(savedCar);
    }

    public CarResponseDTO updateCar(UUID id, CarRequestDTO carRequestDTO){
        CarModel existingCar = carRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Não existe carro com esse ID: " + id));
        existingCar.setModel(carRequestDTO.model());
        existingCar.setYear(carRequestDTO.year());
        existingCar.setColor(carRequestDTO.color());
        existingCar.setPrice(carRequestDTO.price());

        CarModel savedCar = carRepository.save(existingCar);
        return convertToCarResponse(savedCar);
    }

    @Transactional
    public CarResponseDTO deleteCar(UUID id){
        CarModel existingCar = carRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Não existe caro com esse ID: " + id)
        );

        carRepository.delete(existingCar);

        return convertToCarResponse(existingCar);
    }

   /* private boolean carExists(CarRequestDTO carRequestDTO){
        return carRepository.carExist(
                carRequestDTO.model(),
                carRequestDTO.make(),
                carRequestDTO.color(),
                carRequestDTO.year(),
                carRequestDTO.price()
        ).isPresent();
    }*/

    private CarModel convertToCarModel(CarRequestDTO carRequestDTO){
            CarModel carModel = new CarModel();
            BeanUtils.copyProperties(carRequestDTO,carModel);
            return carModel;
    }

    private CarResponseDTO convertToCarResponse(CarModel carModel){
            CarResponseDTO carResponseDTO = new CarResponseDTO();
            BeanUtils.copyProperties(carModel,carResponseDTO);
            return carResponseDTO;
    }

}
