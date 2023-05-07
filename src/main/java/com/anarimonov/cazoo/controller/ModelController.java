package com.anarimonov.cazoo.controller;

import com.anarimonov.cazoo.dto.ModelDto;
import com.anarimonov.cazoo.entity.Model;
import com.anarimonov.cazoo.repository.MakerRepository;
import com.anarimonov.cazoo.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/model")
@RequiredArgsConstructor
public class ModelController {
    private final MakerRepository makerRepository;
    private final ModelRepository modelRepository;
    @GetMapping("/{makerId}")
    private HttpEntity<?> getModelByMaker(@PathVariable String makerId) {
        List<Model> models = modelRepository.findByMakerId(Long.parseLong(makerId));
        return ResponseEntity.ok(models);
    }

    @PostMapping
    private HttpEntity<?> addModel(@RequestBody ModelDto modelDto) {
        Model model = new Model();
        try {

            model.setId(modelDto.getId());
            model.setName(modelDto.getName());
            model.setMaker(makerRepository.findById(modelDto.getMakerId()).orElseThrow());
            modelRepository.save(model);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.valueOf(201)).body("success");
    }

    @DeleteMapping("/{id}")
    private HttpEntity<?> delete(@PathVariable String id) {
        modelRepository.deleteById(Long.parseLong(id));
        return ResponseEntity.ok("successfully deleted");
    }

}
