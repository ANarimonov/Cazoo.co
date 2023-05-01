package com.anarimonov.cazoo.service;

import com.anarimonov.cazoo.dto.SpecificationDto;
import com.anarimonov.cazoo.entity.Specification;
import com.anarimonov.cazoo.repository.CarRepository;
import com.anarimonov.cazoo.repository.SpecificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecificationService {
    public final SpecificationRepository specificationRepository;
    private final CarRepository carRepository;

    public HttpEntity addSpecification(SpecificationDto specificationDto) {
        Specification specification = new Specification();
        specification.setId(specificationDto.getId());
        specification.setCar(carRepository.findById(specificationDto.getCarId()).orElseThrow(() -> new RuntimeException("Car id must not be null")));
        specification.setAcceleration(specificationDto.getAcceleration());
        specification.setCylinders(specificationDto.getCylinders());
        specification.setHeight(specificationDto.getHeight());
        specification.setLength(specificationDto.getLength());
        specification.setValves(specificationDto.getValves());
        specification.setWeight(specificationDto.getWeight());
        specification.setWheelbase(specificationDto.getWheelbase());
        specification.setWidth(specificationDto.getWidth());
        specification.setBootSpace(specificationDto.getBootSpace());
        specification.setEnginePower(specificationDto.getEnginePower());
        specification.setTopSpeed(specificationDto.getTopSpeed());
        specification.setFuelCapacity(specificationDto.getFuelCapacity());
        specificationRepository.save(specification);
        return ResponseEntity.ok("success");
    }
}
