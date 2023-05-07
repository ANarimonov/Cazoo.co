package com.anarimonov.cazoo.repository;

import com.anarimonov.cazoo.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    @Query(nativeQuery = true, value = "select a.id from attachments a " +
            "join cars_attachments ca on a.id = ca.attachments_id " +
            "where cars_id = :car_id")
    List<Long> getByCarId(long car_id);
}
