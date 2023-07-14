package com.anarimonov.cazoo.service;

import com.anarimonov.cazoo.dto.FavoriteDto;
import com.anarimonov.cazoo.entity.Favorite;
import com.anarimonov.cazoo.repository.CarRepository;
import com.anarimonov.cazoo.repository.FavoriteRepository;
import com.anarimonov.cazoo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;


    public HttpEntity<?> getFavoritesByUser(Long userId) {
        List<Favorite> favorites = favoriteRepository.findByUserId(userId);
        return ResponseEntity.ok(favorites);
    }

    public HttpEntity<?> addCarToFavorites(FavoriteDto favoriteDto) {
        Favorite favorite = new Favorite();
        try {
            favorite.setUser(userRepository.findById(favoriteDto.getUserId()).get());
            favorite.setCar(carRepository.findById(favoriteDto.getCarId()).get());
            favoriteRepository.save(favorite);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.status(201).body("Successfully added");
    }

    public HttpEntity<?> deleteFavorite(Long favoriteId) {
        try {
            favoriteRepository.deleteById(favoriteId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Successfully deleted");
    }
}
