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
    private final SpecificationRepository specificationRepository = specificationService.specificationRepository;

    @GetMapping("/{id}")
    private HttpEntity getSpecificationById(@PathVariable String id) {
        Specification specification = specificationRepository.findById(Long.parseLong(id)).orElseThrow();
        return ResponseEntity.ok(specification);
    }

    @PostMapping
    private HttpEntity addSpecification(@RequestBody SpecificationDto specificationDto) {
        return specificationService.addSpecification(specificationDto);
    }

    @DeleteMapping("/{id}")
    private HttpEntity delete(@PathVariable String id) {
        specificationRepository.deleteById(Long.parseLong(id));
        return ResponseEntity.ok("successfully deleted");
    }
}
