package com.anarimonov.cazoo.repository;

import com.anarimonov.cazoo.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
