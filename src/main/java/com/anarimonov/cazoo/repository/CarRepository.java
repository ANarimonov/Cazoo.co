package com.anarimonov.cazoo.repository;

import com.anarimonov.cazoo.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
