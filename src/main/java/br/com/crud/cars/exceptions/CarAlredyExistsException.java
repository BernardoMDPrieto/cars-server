package br.com.crud.cars.exceptions;

public class CarAlredyExistsException extends RuntimeException{

    public CarAlredyExistsException(String message){
        super(message);
    }
}
