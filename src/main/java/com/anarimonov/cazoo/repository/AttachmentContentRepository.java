package com.anarimonov.cazoo.repository;

import com.anarimonov.cazoo.entity.AttachmentContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Long> {
    AttachmentContent findByAttachmentId(Long attachment_id);
}
