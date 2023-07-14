package com.anarimonov.cazoo.controller;

import com.anarimonov.cazoo.dto.SpecificationDto;
import com.anarimonov.cazoo.entity.Specification;
import com.anarimonov.cazoo.repository.SpecificationRepository;
import com.anarimonov.cazoo.service.SpecificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car/specification")
@RequiredArgsConstructor
public class SpecificationController {
    private final SpecificationService specificationService;
    private final SpecificationRepository specificationRepository;

    @GetMapping("/{id}")
    public HttpEntity<?> getSpecificationById(@PathVariable long id) {
        Specification specification = specificationRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(specification);
    }

    @PostMapping
    public HttpEntity<?> addSpecification(@RequestBody SpecificationDto specificationDto) {
        return specificationService.addSpecification(specificationDto);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editSpecification(@PathVariable long id, @RequestBody SpecificationDto specificationDto) {
        return specificationService.editSpecification(specificationDto, id);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable long id) {

        specificationRepository.deleteById(id);
        return ResponseEntity.ok("Successfully deleted");
    }
}
