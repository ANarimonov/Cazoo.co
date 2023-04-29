package com.anarimonov.cazoo.controller;

import com.anarimonov.cazoo.entity.Attachment;
import com.anarimonov.cazoo.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoRepository photoRepository;

    @GetMapping("/{carId}")
    private HttpEntity getAllByCar(@PathVariable String carId) {
//        List<Photo> carPhotos = photoRepository.findAllByCarId(Long.parseLong(carId));
//        return ResponseEntity.ok(carPhotos);
        return null;
    }

    @PostMapping
    private HttpEntity uploadFiles(@RequestPart(value = "file") List<MultipartFile> files) {
        try {
            for (MultipartFile file : files) {
                photoRepository.save(new Attachment(
                        file.getOriginalFilename(),
                        file.getSize(),
                        file.getContentType(),
                        file.getBytes()
                ));
            }
            return ResponseEntity.ok("success");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}")
    private HttpEntity uploadFile(@RequestPart(value = "file") MultipartFile file, @PathVariable String id) {
        try {
            photoRepository.save(new Attachment(
                    Long.parseLong(id),
                    file.getOriginalFilename(),
                    file.getSize(),
                    file.getContentType(),
                    file.getBytes()
            ));
            return ResponseEntity.ok("success");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    private HttpEntity deleteFile(@PathVariable String id) {
        photoRepository.deleteById(Long.parseLong(id));
        return ResponseEntity.ok("successfully deleted");
    }
}
