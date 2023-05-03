package com.anarimonov.cazoo.repository;

import com.anarimonov.cazoo.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findByMakerId(long maker_id);
}
