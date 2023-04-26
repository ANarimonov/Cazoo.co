package com.anarimonov.cazoo.repository;

import com.anarimonov.cazoo.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
