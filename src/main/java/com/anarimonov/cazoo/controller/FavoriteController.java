package com.anarimonov.cazoo.controller;

import com.anarimonov.cazoo.dto.FavoriteDto;
import com.anarimonov.cazoo.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
public class FavoriteController {
    private final FavoriteService favoriteService;
    @GetMapping("/{userId}")
    private HttpEntity<?> getFavorites(@PathVariable String userId) {
        return favoriteService.getFavoritesByUser(userId);
    }

    @PostMapping
    private HttpEntity<?> addToFavorites(@RequestBody FavoriteDto favoriteDto) {
        return favoriteService.addCarToFavorites(favoriteDto);
    }

    @DeleteMapping("/{favoriteId}")
    private HttpEntity<?> removeFromFavorites(@PathVariable String favoriteId) {
        return favoriteService.deleteFavorite(favoriteId);
    }
}
