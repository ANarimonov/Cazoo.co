package com.anarimonov.cazoo.service;

import com.anarimonov.cazoo.dto.ImperfectionDto;
import com.anarimonov.cazoo.entity.Imperfection;
import com.anarimonov.cazoo.repository.AttachmentRepository;
import com.anarimonov.cazoo.repository.CarRepository;
import com.anarimonov.cazoo.repository.ImperfectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImperfectionService {
    public final ImperfectionRepository imperfectionRepository;
    private final CarRepository carRepository;
    private final AttachmentRepository attachmentRepository;

    public HttpEntity<?> addImperfection(ImperfectionDto imperfectionDto) {
        Imperfection imperfection = new Imperfection();
        imperfection.setCar(carRepository.findById(imperfectionDto.getCarId()).orElseThrow(() -> new RuntimeException("Car id must not be null")));
        imperfection.setAttachment(attachmentRepository.findById(imperfectionDto.getAttachmentId()).orElseThrow(() -> new RuntimeException("Attachment id must not be null")));
        imperfection.setDescription(imperfectionDto.getDescription());
        imperfectionRepository.save(imperfection);
        return ResponseEntity.status(HttpStatus.valueOf(201)).body("Successfully added");
    }

    @SneakyThrows
    public HttpEntity<?> editImperfection(long id, ImperfectionDto imperfectionDto) {
        Optional<Imperfection> optionalImperfection = imperfectionRepository.findById(id);
        if (optionalImperfection.isEmpty())
            return ResponseEntity.status(404).body("Imperfection not found");
        Imperfection imperfection = optionalImperfection.get();
        long attachmentId = imperfectionDto.getAttachmentId();
        if (attachmentId != 0)
            imperfection.setAttachment(attachmentRepository.findById(attachmentId).orElseThrow(NoSuchFieldException::new));
        long carId = imperfectionDto.getCarId();
        if (carId != 0)
            imperfection.setCar(carRepository.findById(carId).orElseThrow(NoSuchFieldException::new));
        String description = imperfectionDto.getDescription();
        if (description != null)
            imperfection.setDescription(description);
        imperfectionRepository.save(imperfection);
        return ResponseEntity.ok("Successfully updated");
    }


}
