package com.anarimonov.cazoo.repository;

import com.anarimonov.cazoo.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PhotoRepository extends JpaRepository<Attachment, Long> {
//    @Query(nativeQuery = true, value = "select * from attachments ")
//    List<Photo> findAllByCarId(Long carId);
}
