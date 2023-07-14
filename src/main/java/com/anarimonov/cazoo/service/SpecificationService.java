package com.anarimonov.cazoo.service;

import com.anarimonov.cazoo.dto.SpecificationDto;
import com.anarimonov.cazoo.entity.Specification;
import com.anarimonov.cazoo.repository.CarRepository;
import com.anarimonov.cazoo.repository.SpecificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class SpecificationService {
    public final SpecificationRepository specificationRepository;
    private final CarRepository carRepository;

    public HttpEntity<?> addSpecification(SpecificationDto specificationDto) {
        Specification specification = specificationSetter(null, specificationDto);
        specificationRepository.save(specification);
        return ResponseEntity.status(HttpStatus.valueOf(201)).body("Successfully added");
    }

    public HttpEntity<?> editSpecification(SpecificationDto specificationDto, Long id) {
        Specification specification = specificationSetter(id, specificationDto);
        specificationRepository.save(specification);
        return ResponseEntity.ok().body("Successfully updated");
    }

    private Specification specificationSetter(Long id, SpecificationDto specificationDto) {
        Specification specification = id == null ? new Specification() : specificationRepository.findById(id).orElseThrow();
        specification.setCar(carRepository.findById(specificationDto.getCarId()).orElseThrow(() -> new RuntimeException("Car id must not be null")));
        Arrays.stream(SpecificationDto.class.getDeclaredFields())
                .forEach(field -> setPropertyIfNotNull(field, specificationDto, specification));
        return specification;
    }

    private void setPropertyIfNotNull(Field field, SpecificationDto dtoObj, Specification obj) {
        try {
            field.setAccessible(true);
            Object value = field.get(dtoObj);
            if (value != null) {
                Field specificationField = Specification.class.getDeclaredField(field.getName());
                specificationField.setAccessible(true);
                specificationField.set(obj, value);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
