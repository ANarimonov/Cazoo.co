package com.anarimonov.cazoo.repository;

import com.anarimonov.cazoo.entity.Car;
import com.anarimonov.cazoo.projection.CarProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    @Query(nativeQuery = true, value = "select id, body_type as bodyType, color, engine, features, fuel_type as fuelType, gearbox, manufactured_year as manufacturedYear, mileage, price, registration_date as registrationDate, maker_id as makerId, model_id as modelId from cars")
    List<CarProjection> getAll();

    @Query(nativeQuery = true, value = "select id, body_type as bodyType, color, engine, features, fuel_type as fuelType, gearbox, manufactured_year as manufacturedYear, mileage, price, registration_date as registrationDate, maker_id as makerId, model_id as modelId from cars where id = :id")
    CarProjection getById(long id);

}
