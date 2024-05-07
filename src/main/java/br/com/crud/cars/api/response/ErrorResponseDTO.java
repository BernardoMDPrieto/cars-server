package br.com.crud.cars.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseDTO extends RuntimeException{
    public ErrorResponseDTO(String message){
        super(message);
    }
}
