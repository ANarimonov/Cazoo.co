package com.anarimonov.cazoo.controller;

import com.anarimonov.cazoo.entity.Attachment;
import com.anarimonov.cazoo.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentRepository attachmentRepository;

    @PostMapping
    private HttpEntity uploadFiles(@RequestPart(value = "file") MultipartFile file) {
        try {
            attachmentRepository.save(new Attachment(
                    file.getOriginalFilename(),
                    file.getSize(),
                    file.getContentType(),
                    file.getBytes()
            ));
            return ResponseEntity.ok("success");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/{id}")
    private HttpEntity uploadFile(@RequestPart(value = "file") MultipartFile file, @PathVariable String id) {
        try {
            attachmentRepository.save(new Attachment(
                    Long.parseLong(id),
                    file.getOriginalFilename(),
                    file.getSize(),
                    file.getContentType(),
                    file.getBytes()
            ));
            return ResponseEntity.ok("success");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    private HttpEntity deleteFile(@PathVariable String id) {
        attachmentRepository.deleteById(Long.parseLong(id));
        return ResponseEntity.ok("successfully deleted");
    }
}
