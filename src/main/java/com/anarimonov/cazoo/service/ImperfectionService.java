package com.anarimonov.cazoo.service;

import com.anarimonov.cazoo.dto.ImperfectionDto;
import com.anarimonov.cazoo.entity.Imperfection;
import com.anarimonov.cazoo.repository.AttachmentRepository;
import com.anarimonov.cazoo.repository.CarRepository;
import com.anarimonov.cazoo.repository.ImperfectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImperfectionService {
    public final ImperfectionRepository imperfectionRepository;
    private final CarRepository carRepository;
    private final AttachmentRepository attachmentRepository;

    public HttpEntity addImperfection(ImperfectionDto imperfectionDto) {
        Imperfection imperfection = new Imperfection();
        imperfection.setId(imperfectionDto.getId());
        imperfection.setDescription(imperfectionDto.getDescription());
        imperfection.setCar(carRepository.findById(imperfectionDto.getCarId()).orElseThrow(() -> new RuntimeException("Car id must not be null")));
        imperfection.setAttachment(attachmentRepository.findById(imperfectionDto.getAttachmentId()).orElseThrow(() -> new RuntimeException("Attachment id must not be null")));
        imperfectionRepository.save(imperfection);
        return ResponseEntity.ok("success");
    }
}
