package com.anarimonov.cazoo.controller;

import com.anarimonov.cazoo.entity.Maker;
import com.anarimonov.cazoo.repository.MakerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/maker")
@RequiredArgsConstructor
public class MakerController {
    private final MakerRepository makerRepository;
    @GetMapping
    public HttpEntity<?> getAllMaker() {
        return ResponseEntity.ok(makerRepository.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getMaker(@PathVariable long id) {
        return ResponseEntity.ok(makerRepository.findById(id).orElseThrow());
    }

    @PostMapping
    public HttpEntity<?> addMaker(@RequestBody Maker maker) {
        makerRepository.save(maker);
        return ResponseEntity.status(HttpStatus.valueOf(201)).body("success");
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteMaker(@PathVariable long id) {
        makerRepository.deleteById(id);
        return ResponseEntity.ok("success");
    }
}
