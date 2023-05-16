package com.anarimonov.cazoo.repository;

import com.anarimonov.cazoo.entity.Maker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MakerRepository extends JpaRepository<Maker, Long> {
    @Query(nativeQuery = true, value = "select makers.* from makers join cars c on makers.id = c.maker_id where c.id = :carId")
    Maker getByCarId(long carId);
}
