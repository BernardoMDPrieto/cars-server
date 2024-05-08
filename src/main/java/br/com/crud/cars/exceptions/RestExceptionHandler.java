package br.com.crud.cars.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CarAlredyExistsException.class)
    private ResponseEntity<String> carExistsErrorHandler(CarAlredyExistsException exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Car alredy exists!");
    }

    @ExceptionHandler(CarNotFoundException.class)
    private ResponseEntity<String> carNotFoundErrorHandler(CarNotFoundException exception){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found by id");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<String> handleValidationExceptions(ConstraintViolationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
