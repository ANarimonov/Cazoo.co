package com.anarimonov.cazoo.repository;

import com.anarimonov.cazoo.entity.Imperfection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ImperfectionRepository extends JpaRepository<Imperfection, Long> {
    Imperfection findByCarId(Long car_id);
}
