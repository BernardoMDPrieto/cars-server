package br.com.crud.cars.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.UUID;

@Entity()
@Table(name="cars")
@Data
public class CarModel implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "color")
    private String color;

    @Column(name = "year")
    private int year;

    @Column(name = "price")
    private BigDecimal price;

    

}
