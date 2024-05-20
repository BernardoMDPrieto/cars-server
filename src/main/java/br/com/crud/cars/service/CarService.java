package br.com.crud.cars.service;

import br.com.crud.cars.api.filters.CarFilterDto;
import br.com.crud.cars.api.filters.CarSpecification;
import br.com.crud.cars.api.request.CarRequestDTO;
import br.com.crud.cars.api.response.CarResponseDTO;
import br.com.crud.cars.exceptions.CarAlredyExistsException;
import br.com.crud.cars.exceptions.CarNotFoundException;
import br.com.crud.cars.model.CarModel;
import br.com.crud.cars.repository.CarRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Page<CarResponseDTO> getAllCars(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<CarModel> carPage = carRepository.findAll(pageable);

        List<CarResponseDTO> carResponseDTOList = carPage.getContent().stream().map(this::convertToCarResponse).collect(Collectors.toList());

        return new PageImpl<>(carResponseDTOList,pageable,carPage.getTotalElements());
    }


    public CarResponseDTO getOneCar(UUID id){
        CarModel existingCar = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(""));
        return convertToCarResponse(existingCar);
    }

    public Page<CarResponseDTO> getCarsBySpecification(CarFilterDto carFilterDto,
                                                       Pageable pageable){
        Specification<CarModel> spec = CarSpecification.bySpecification(carFilterDto);

        Page<CarModel> carPage = carRepository.findAll(spec,pageable);
        List<CarResponseDTO> carResponseDTOList = carPage.getContent().stream().map(this::convertToCarResponse).collect(Collectors.toList()) ;

        return new PageImpl<>(carResponseDTOList,pageable,carPage.getTotalElements());

    }

    @Transactional
    public CarResponseDTO saveCar(CarRequestDTO carRequestDTO){
        CarModel carModel = convertToCarModel(carRequestDTO);

        Specification<CarModel> spec = CarSpecification.byCarAttributes(carModel);
        if(carRepository.findOne(spec).isPresent()){
            throw new CarAlredyExistsException("");
        }

        CarModel savedCar = carRepository.save(carModel);
        return convertToCarResponse(savedCar);
    }

    @Transactional
    public CarResponseDTO updateCar(UUID id, CarRequestDTO carRequestDTO){
        CarModel existingCar = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(""));
        BeanUtils.copyProperties(carRequestDTO,existingCar);
        CarModel savedCar = carRepository.save(existingCar);
        return convertToCarResponse(savedCar);
    }

    @Transactional
    public CarResponseDTO updateQuantity(UUID id, Integer newQuantity){
        CarModel existingCar = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(""));
        existingCar.setQuantity(newQuantity);
        carRepository.save(existingCar);
        return convertToCarResponse(existingCar);
    }

    @Transactional
    public CarResponseDTO deleteCar(UUID id){
        CarModel existingCar = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(""));
        carRepository.delete(existingCar);
        return convertToCarResponse(existingCar);
    }



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
