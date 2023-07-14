package com.anarimonov.cazoo.controller;

import com.anarimonov.cazoo.dto.ImperfectionDto;
import com.anarimonov.cazoo.entity.Imperfection;
import com.anarimonov.cazoo.repository.ImperfectionRepository;
import com.anarimonov.cazoo.service.ImperfectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/car/imperfections")
@RequiredArgsConstructor
public class ImperfectionController {
    private final ImperfectionService imperfectionService;
    private final ImperfectionRepository imperfectionRepository;

    @GetMapping("/by-car/{carId}")
    public HttpEntity<?> getImperfectionsByCarId(@PathVariable long carId) {
        List<Imperfection> imperfections = imperfectionRepository.findByCarId(carId);
        return ResponseEntity.ok(imperfections);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getImperfectionById(@PathVariable long id) {
        Imperfection imperfection = imperfectionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Imperfection not found"));
        return ResponseEntity.ok(imperfection);
    }

    @PostMapping("/add")
    public HttpEntity<?> addImperfection(@RequestBody ImperfectionDto imperfectionDto) {
        return imperfectionService.addImperfection(imperfectionDto);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editImperfection(@PathVariable long id, @RequestBody ImperfectionDto imperfectionDto) {
        return imperfectionService.editImperfection(id,imperfectionDto);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable long id) {
        if (!imperfectionRepository.existsById(id)) return ResponseEntity.status(404).body("Imperfection not found");
        imperfectionRepository.deleteById(id);
        return ResponseEntity.ok("Successfully deleted");
    }
}
