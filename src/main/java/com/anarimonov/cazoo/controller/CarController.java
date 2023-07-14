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
    public HttpEntity<?> getCars() {
        return carService.getCars();
    }

    @GetMapping("/filter")
    public HttpEntity<?> getCarsByFilter(CarSearchDto carSearchDto) {
        return carService.getCarsByFilter(carSearchDto, false);
    }

    @GetMapping("/filter/count")
    public HttpEntity<?> getCarsCountByFilter(CarSearchDto carSearchDto) {
        return carService.getCarsByFilter(carSearchDto, true);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getCarById(@PathVariable long id) {
        return carService.getCarById(id);
    }

    @PostMapping
    public HttpEntity<?> addCar(@RequestBody CarDto carDto) {
        return carService.addCar(carDto);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editCar(@PathVariable long id, @RequestBody CarDto carDto) {
        return carService.editCar(id, carDto);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCar(@PathVariable long id) {
        return carService.deleteCar(id);
    }
}
