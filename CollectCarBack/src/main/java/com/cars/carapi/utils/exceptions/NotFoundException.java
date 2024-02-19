package com.cars.carapi.utils.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotFoundException extends RuntimeException{

    public NotFoundException(Long id){
        super("Registro de carro com id "+ id +" não encontrado");
        log.warn("O dado de id "+id+" não foi encontrado");
    }
}
