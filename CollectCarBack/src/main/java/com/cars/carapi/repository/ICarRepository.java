package com.cars.carapi.repository;

import com.cars.carapi.model.Car;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {

    interface Specs{

        static Specification<Car> byBrandCar(String brandPattern){
            return(root, query, builder) ->
                    builder.like(root.get("brandCar"), "%"+brandPattern+"%");
        }


        static Specification<Car> byModelCar(String modelPattern){
            return(root, query, builder) ->
                    builder.like(root.get("modelCar"), "%"+modelPattern+"%");
        }

        static Specification<Car> byYearCar(String modelPattern){
            return(root, query, builder) ->
                    builder.like(root.get("yearCar"), "%"+modelPattern+"%");
        }

        static Specification<Car> byColorCar(String colorPattern){
            return(root, query, builder) ->
                    builder.like(root.get("colorCar"), "%"+colorPattern+"%");
        }

    }

}
