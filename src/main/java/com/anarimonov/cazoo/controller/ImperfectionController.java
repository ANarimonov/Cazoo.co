package com.anarimonov.cazoo.controller;

import com.anarimonov.cazoo.dto.ImperfectionDto;
import com.anarimonov.cazoo.entity.Imperfection;
import com.anarimonov.cazoo.repository.ImperfectionRepository;
import com.anarimonov.cazoo.service.ImperfectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car/imperfections")
@RequiredArgsConstructor
public class ImperfectionController {
    private final ImperfectionService imperfectionService;
    private final ImperfectionRepository imperfectionRepository;
    @GetMapping("/by-car/{carId}")
    private HttpEntity getImperfectionsByCarId(@PathVariable String carId) {
        Imperfection imperfection = imperfectionRepository.findByCarId(Long.parseLong(carId));
        return ResponseEntity.ok(imperfection);
    }

    @GetMapping("/{imperfectionId}")
    private HttpEntity getImperfectionById(@PathVariable String imperfectionId) {
        Imperfection imperfection = imperfectionRepository.findById(Long.parseLong(imperfectionId)).orElseThrow(() -> new RuntimeException("Imperfection not found"));
        return ResponseEntity.ok(imperfection);
    }

    @PostMapping("/add")
    private HttpEntity addImperfection(@RequestBody ImperfectionDto imperfectionDto) {
        return imperfectionService.addImperfection(imperfectionDto);
    }

    @DeleteMapping("/{id}")
    private HttpEntity delete(@PathVariable String id) {
        imperfectionRepository.deleteById(Long.parseLong(id));
        return ResponseEntity.ok("successfully deleted");
    }
}
