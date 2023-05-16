package com.anarimonov.cazoo.controller;

import com.anarimonov.cazoo.entity.Attachment;
import com.anarimonov.cazoo.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentRepository attachmentRepository;

    @GetMapping("/{attachmentId}")
    private ResponseEntity<?> getFile(@PathVariable String attachmentId) {
        Attachment attachment = attachmentRepository.findById(Long.parseLong(attachmentId)).orElseThrow();
        try {
            ByteArrayResource resource = new ByteArrayResource(attachment.getData());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + attachment.getName())
                    .contentType(MediaType.valueOf(attachment.getContentType()))
                    .contentLength(attachment.getData().length)
                    .body(resource);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping
    private HttpEntity<?> uploadFiles(@RequestPart(value = "file") List<MultipartFile> files) {
        try {
            List<Long> ids = new ArrayList<>();
            for (MultipartFile file : files) {
                Attachment attachment = attachmentRepository.save(new Attachment(
                        file.getOriginalFilename(),
                        file.getSize(),
                        file.getContentType(),
                        file.getBytes()
                ));
                ids.add(attachment.getId());
            }
            return ResponseEntity.ok(ids);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}")
    private HttpEntity<?> uploadFile(@RequestPart(value = "file") MultipartFile file, @PathVariable String id) {
        try {
            attachmentRepository.save(new Attachment(
                    Long.parseLong(id),
                    file.getOriginalFilename(),
                    file.getSize(),
                    file.getContentType(),
                    file.getBytes()
            ));
            return ResponseEntity.status(HttpStatus.valueOf(201)).body("success");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    private HttpEntity<?> deleteFile(@PathVariable String id) {
        attachmentRepository.deleteById(Long.parseLong(id));
        return ResponseEntity.ok("successfully deleted");
    }
}
