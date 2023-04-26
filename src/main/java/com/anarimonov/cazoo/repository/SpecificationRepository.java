package com.anarimonov.cazoo.repository;

import com.anarimonov.cazoo.entity.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecificationRepository extends JpaRepository<Specification,Long> {
}
