package com.anarimonov.cazoo.controller;

import com.anarimonov.cazoo.dto.CarDto;
import com.anarimonov.cazoo.dto.CarSearchDto;
import com.anarimonov.cazoo.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping
    private HttpEntity getCars(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size) {
        try {
            PageRequest pageRequest = PageRequest.of(page - 1, size);
            return carService.getCars(pageRequest);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/filter")
    private HttpEntity getCarsByFilter(CarSearchDto carSearchDto) {
        return carService.getCars(carSearchDto);
    }

    @PostMapping
    private HttpEntity addCar(@RequestBody CarDto carDto) {
        return carService.addCar(carDto);
    }

    @DeleteMapping("/{id}")
    private HttpEntity deleteCar(@PathVariable String id) {
        return carService.deleteCar(id);
    }
}
