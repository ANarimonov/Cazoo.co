package com.anarimonov.cazoo.repository;

import com.anarimonov.cazoo.entity.Maker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MakerRepository extends JpaRepository<Maker, Long> {
}
