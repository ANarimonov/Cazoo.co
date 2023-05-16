package com.anarimonov.cazoo.repository;

import com.anarimonov.cazoo.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findByMakerId(long maker_id);

    @Query(nativeQuery = true, value = "select models.* from models join cars c on models.id = c.model_id where c.id = :carId")
    Model getByCarId(long carId);
}
