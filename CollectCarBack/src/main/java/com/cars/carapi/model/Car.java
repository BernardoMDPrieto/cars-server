package com.cars.carapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCar;

    @NotBlank
    @Length(max = 30)
    @NotNull
    @Column(length = 30, nullable = false)
    private String brandCar;

    @NotBlank
    @Length(max = 30)
    @NotNull
    @Column(length = 30, nullable = false)
    private String modelCar;

    @NotBlank
    @Length(max = 4)
    @NotNull
    @Column(length = 4, nullable = false)
    private String yearCar;

    @NotBlank
    @Length(max = 15)
    @NotNull
    @Column(length = 15, nullable = false)
    private String colorCar;

}
