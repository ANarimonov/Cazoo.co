package com.anarimonov.cazoo.repository;

import com.anarimonov.cazoo.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
}
