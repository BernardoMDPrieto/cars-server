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

    public static Specification<CarModel> byQuantity(int quantity) {
        return (root, query, builder) -> builder.equal(root.get("quantity"), quantity);
    }
    public static Specification<CarModel> bySepecification(String make,
                                                                      String model,
                                                                      String color,
                                                                      int year,
                                                                      BigDecimal price,
                                                                      int quantity) {
        Specification<CarModel> spec = Specification.where(null);
        if (make != null && !make.isEmpty()) {
            spec = spec.and(byMake(make));
        }
        if (model != null && !model.isEmpty()) {
            spec = spec.and(byModel(model));
        }
        if (color != null && !color.isEmpty()) {
            spec = spec.and(byColor(color));
        }
        if (year != 0) {
            spec = spec.and(byYear(year));
        }
        if (price != null) {
            spec = spec.and(byPrice(price));
        }
        if (quantity != 0) {
            spec = spec.and(byQuantity(quantity));
        }
        return spec;
    }
}
