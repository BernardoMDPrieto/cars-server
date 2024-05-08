package br.com.crud.cars.api.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CarRequestDTO(
        @NotNull(message = "É preciso informar a marca do veículo!")
        @NotBlank(message = "É preciso informar a marca do veículo!")
        String make,
        @NotNull(message = "É preciso informar o modelo do veículo!")
        @NotBlank(message = "É preciso informar o modelo do veículo!")
        String model,
        @NotNull(message = "É preciso informar a cor do veículo!")
        @NotBlank(message = "É preciso informar a cor do veículo!")
        String color,
        @NotNull(message = "É preciso informar o ano do veículo!")
        @Positive(message = "O ano deve ser maior que zero")
        Integer year,
        @NotNull(message = "É preciso informar a quantidade de veículos!")
        @PositiveOrZero(message = "A quantidade de veículos não pode ser negativa")
        Integer quantity,
        @NotNull(message = "É preciso informar o preço do veículo!")
        @Positive(message = "O preço deve ser maior que zero")
        BigDecimal price
) {}
