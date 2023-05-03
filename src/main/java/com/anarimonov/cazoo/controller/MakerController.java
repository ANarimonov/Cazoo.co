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
    private HttpEntity getAllMaker() {
        return ResponseEntity.ok(makerRepository.findAll());
    }

    @GetMapping("/{id}")
    private HttpEntity getMaker(@PathVariable String id) {
        return ResponseEntity.ok(makerRepository.findById(Long.parseLong(id)).orElseThrow());
    }

    @PostMapping
    private HttpEntity addMaker(@RequestBody Maker maker) {
        makerRepository.save(maker);
        return ResponseEntity.status(HttpStatus.valueOf(201)).body("success");
    }

    @DeleteMapping("/{id}")
    private HttpEntity deleteMaker(@PathVariable String id) {
        makerRepository.deleteById(Long.parseLong(id));
        return ResponseEntity.ok("success");
    }
}
