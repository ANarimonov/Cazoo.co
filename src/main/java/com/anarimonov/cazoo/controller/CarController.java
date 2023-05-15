package com.anarimonov.cazoo.controller;

import com.anarimonov.cazoo.dto.CarDto;
import com.anarimonov.cazoo.dto.CarSearchDto;
import com.anarimonov.cazoo.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping
    private HttpEntity<?> getCars() {
            return carService.getCars();
    }

    @GetMapping("/filter")
    private HttpEntity<?> getCarsByFilter(CarSearchDto carSearchDto) {
        return carService.getCars(carSearchDto, false);
    }

    @GetMapping("/filter/count")
    private HttpEntity<?> getCarsCountByFilter(CarSearchDto carSearchDto) {
        return carService.getCars(carSearchDto, true);
    }

    @GetMapping("/{id}")
    private HttpEntity<?> getCarById(@PathVariable String id) {
        return carService.getCarById(Long.parseLong(id));
    }

    @PostMapping
    private HttpEntity<?> addCar(@RequestBody CarDto carDto) {
        return carService.addCar(carDto);
    }

    @DeleteMapping("/{id}")
    private HttpEntity<?> deleteCar(@PathVariable String id) {
        return carService.deleteCar(id);
    }
}
