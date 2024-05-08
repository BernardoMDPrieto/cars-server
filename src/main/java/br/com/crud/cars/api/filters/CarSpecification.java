package br.com.crud.cars.api.filters;

import br.com.crud.cars.model.CarModel;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class CarSpecification {
    public static Specification<CarModel> byMake(String make) {
        return (root, query, builder) -> builder.equal(root.get("make"), make);
    }
    public static Specification<CarModel> byModel(String model) {
        return (root, query, builder) -> builder.equal(root.get("model"), model);
    }
    public static Specification<CarModel> byColor(String color) {
        return (root, query, builder) -> builder.equal(root.get("color"),color);
    }
    public static Specification<CarModel> byYear(int year) {
        return (root, query, builder) -> builder.equal(root.get("year"), year);
    }
    public static Specification<CarModel> byCarAtributes(CarModel carModel){
        Specification<CarModel> spec = Specification.where(
                byMake((carModel.getMake()))
                .and(byColor((carModel.getColor()))
                .and(byYear((carModel.getYear()))
                .and(byModel((carModel.getModel()))))));
        return spec;
    }
    public static Specification<CarModel> byPrice(BigDecimal price) {
        return (root, query, builder) -> builder.equal(root.get("price"), price);
    }

    public static Specification<CarModel> byQuantityGreaterThan(int quantity) {
        return (root, query, builder) -> builder.equal(root.get("quantity"), quantity);
    }
}
