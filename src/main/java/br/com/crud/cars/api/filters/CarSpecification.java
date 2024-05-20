package br.com.crud.cars.api.filters;

import br.com.crud.cars.model.CarModel;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class CarSpecification {
    public static Specification<CarModel> byMake(String make) {
        return (root, query, builder) -> builder.like(root.get("make"), "%" + make + "%");
    }

    public static Specification<CarModel> byModel(String model) {
        return (root, query, builder) -> builder.like(root.get("model"), "%" + model + "%");
    }

    public static Specification<CarModel> byColor(String color) {
        return (root, query, builder) -> builder.like(root.get("color"), "%" + color + "%");
    }

    public static Specification<CarModel> byYear(Integer year) {
        return (root, query, builder) -> builder.equal(root.get("year"), year);
    }

    public static Specification<CarModel> byCarAttributes(CarModel carModel) {
        return Specification.where(byMake(carModel.getMake()))
                .and(byColor(carModel.getColor()))
                .and(byYear(carModel.getYear()))
                .and(byModel(carModel.getModel()));
    }

    public static Specification<CarModel> byPrice(BigDecimal price) {
        return (root, query, builder) -> builder.equal(root.get("price"), price);
    }

    public static Specification<CarModel> byQuantity(Integer quantity) {
        return (root, query, builder) -> builder.equal(root.get("quantity"), quantity);
    }

    public static Specification<CarModel> bySpecification(CarFilterDto carFilterDto) {
        if (carFilterDto == null) {
            throw new IllegalArgumentException("carFilterDto não pode ser nulo");
        }

        Specification<CarModel> spec = Specification.where(null);

        if (carFilterDto.getMake() != null && !carFilterDto.getMake().isEmpty()) {
            spec = spec.and(byMake(carFilterDto.getMake()));
        }
        if (carFilterDto.getModel() != null && !carFilterDto.getModel().isEmpty()) {
            spec = spec.and(byModel(carFilterDto.getModel()));
        }
        if (carFilterDto.getColor() != null && !carFilterDto.getColor().isEmpty()) {
            spec = spec.and(byColor(carFilterDto.getColor()));
        }
        if (carFilterDto.getYear() != null) {
            spec = spec.and(byYear(carFilterDto.getYear()));
        }
        if (carFilterDto.getPrice() != null) {
            spec = spec.and(byPrice(carFilterDto.getPrice()));
        }
        if (carFilterDto.getQuantity() != null) {
            spec = spec.and(byQuantity(carFilterDto.getQuantity()));
        }

        return spec;
    }
}
